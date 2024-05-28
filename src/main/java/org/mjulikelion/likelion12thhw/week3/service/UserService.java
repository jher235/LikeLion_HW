package org.mjulikelion.likelion12thhw.week3.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.GetOrganizationsResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.OrganizationResponse;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.repository.UserOrganizationRepository;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserOrganizationRepository userOrganizationRepository;
    private final PasswordHashEncryption passwordHashEncryption;


    public void modify(ModifyUserDto modifyUserDto, User user) {
        user.modify(modifyUserDto.getEmail(), modifyUserDto.getName(), passwordHashEncryption.encrypt(modifyUserDto.getPassword()));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public GetOrganizationsResponseData getOrganizations(User user) {
        return new GetOrganizationsResponseData(userOrganizationRepository.findAllByUser(user).stream()
                .map(i -> OrganizationResponse.organizationResponse(i.getOrganization()))
                .collect(Collectors.toList()));
    }


    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

}
