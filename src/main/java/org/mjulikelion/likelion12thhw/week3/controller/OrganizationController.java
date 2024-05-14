package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thhw.annotation.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.Organization.RegisterOrganizationDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.GetOrganizationsResponseData;
import org.mjulikelion.likelion12thhw.week3.dto.response.user.GetUserResponseData;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.service.OrganizationService;
import org.mjulikelion.likelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> registerOrganization(
            @RequestBody @Valid RegisterOrganizationDto registerOrganizationDto, @AuthenticatedUser User user) {
        organizationService.register(registerOrganizationDto, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "단체 생성 완료"), HttpStatus.CREATED);
    }


    @PostMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<Void>> joinOrganization(
            @AuthenticatedUser User user, @PathVariable UUID organizationId) {
        organizationService.join(user, organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 가입 완료"), HttpStatus.OK);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<Void>> withdrawOrganization(
            @AuthenticatedUser User user, @PathVariable UUID organizationId) {
        organizationService.withdraw(user, organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 탈퇴 완료"), HttpStatus.OK);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<GetUserResponseData>> getOrganizationUsers(@PathVariable UUID organizationId) {
        GetUserResponseData getUserResponseData = organizationService.getUsers(organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 소속 멤버 조회 성공", getUserResponseData), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<GetOrganizationsResponseData>> getOrganizations(@AuthenticatedUser User user) {
        GetOrganizationsResponseData getOrganizationsResponseData = userService.getOrganizations(user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저의 조직 목록", getOrganizationsResponseData), HttpStatus.OK);
    }


}
