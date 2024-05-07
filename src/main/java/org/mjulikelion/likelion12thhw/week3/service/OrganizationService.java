package org.mjulikelion.likelion12thhw.week3.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.dto.request.Organization.RegisterOrganizationDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.GetUsersDto;
import org.mjulikelion.likelion12thhw.week3.exception.ConflictException;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.model.Organization;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.model.UserOrganization;
import org.mjulikelion.likelion12thhw.week3.repository.OrganizationRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserOrganizationRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
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
    private final UserRepository userRepository;

    public void register(RegisterOrganizationDto registerOrganizationDto) {
        String name = normalizeName(registerOrganizationDto.getName());

        if (organizationRepository.existsByName(name)) {
            throw new ConflictException("이미 존재하는 단체입니다.");
        }
        Organization organization = Organization.builder()
                .name(name)
                .build();

        organizationRepository.save(organization);
        log.info(String.valueOf(organization.getId()));
    }

    public void join(UUID userId, UUID organizationId) {
        Organization organization = this.findById(organizationId);

        UserOrganization userOrganization = UserOrganization.builder()
                .user(this.findUserByUserId(userId))
                .organization(organization)
                .build();

        if (this.isExist(userId, organization)) {
            throw new ConflictException("이미 가입된 단체입니다.");
        }
        userOrganizationRepository.save(userOrganization);
    }

    public void withdraw(UUID userId, UUID organizationId) {
        User user = this.findUserByUserId(userId);
        Organization organization = this.findById(organizationId);
        UserOrganization userOrganization = userOrganizationRepository.findByUserAndOrganization(user, organization)
                .orElseThrow(() -> new NotFoundException("가입되지 않은 단체입니다."));
        userOrganizationRepository.delete(userOrganization);
    }

    public GetUsersDto getUsers(UUID id) {
        Organization organization = this.findById(id);
//        List<UserOrganization> userOrganizations = organization.getUserOrganization();
        GetUsersDto getUsersDto = new GetUsersDto(organization.getUserOrganization().stream()
                .map(u -> u.getUser()).collect(Collectors.toList()));
        return getUsersDto;
    }

    //이름에 통일성 부여
    private String normalizeName(String name) {
        return name.strip().replace(" ", "_");
    }

    private Organization findById(UUID id) {
        return organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 단체입니다."));
    }

    private User findUserByUserId(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
    }

    private boolean isExist(UUID userId, Organization organization) {
        UserOrganization userOrganization = UserOrganization.builder()
                .user(this.findUserByUserId(userId))
                .organization(organization)
                .build();

        Example<UserOrganization> example = Example.of(userOrganization);
        return userOrganizationRepository.exists(example);
    }

}
