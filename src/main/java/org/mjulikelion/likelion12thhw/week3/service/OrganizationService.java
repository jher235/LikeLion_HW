package org.mjulikelion.likelion12thhw.week3.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.dto.request.Organization.RegisterOrganizationDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.user.GetUserResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.user.UserResponse;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.Organization;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.model.UserOrganization;
import org.mjulikelion.likelion12thhw.week3.repository.OrganizationRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserOrganizationRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationService {

    private final UserOrganizationRepository userOrganizationRepository;
    private final OrganizationRepository organizationRepository;

    public void register(RegisterOrganizationDto registerOrganizationDto, User user) {
        String name = registerOrganizationDto.getName().strip();

        if (organizationRepository.existsByName(name)) {
            throw new ConflictException(ErrorCode.ORGANIZATION_CONFLICT);
        }
        Organization organization = Organization.builder()
                .name(name)
                .build();

        organizationRepository.save(organization);

        UserOrganization userOrganization = UserOrganization.builder()
                .user(user)
                .organization(organization)
                .build();
        userOrganizationRepository.save(userOrganization);

        log.info(String.valueOf(organization.getId()));
    }

    public void join(User user, UUID organizationId) {
        Organization organization = this.findById(organizationId);

        UserOrganization userOrganization = UserOrganization.builder()
                .user(user)
                .organization(organization)
                .build();

        Example<UserOrganization> example = Example.of(userOrganization);
        if (userOrganizationRepository.exists(example)) {
            throw new ConflictException(ErrorCode.ORGANIZATION_JOIN_CONFLICT);
        }
        userOrganizationRepository.save(userOrganization);
    }

    public void withdraw(User user, UUID organizationId) {
        Organization organization = this.findById(organizationId);
        UserOrganization userOrganization = userOrganizationRepository.findByUserAndOrganization(user, organization)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_JOIN_NOT_FOUND));
        userOrganizationRepository.delete(userOrganization);
    }

    public GetUserResponseData getUsers(UUID id) {
        Organization organization = this.findById(id);
        GetUserResponseData getUserResponseData = new GetUserResponseData(organization.getUserOrganization().stream()
                .map(u -> new UserResponse(u.getUser().getName())).collect(Collectors.toList()));
        return getUserResponseData;
    }

    private Organization findById(UUID id) {
        return organizationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_NOT_FOUND));
    }

}
