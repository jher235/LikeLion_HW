package org.mjulikelion.likelion12thhw.week3.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.Organization.RegisterOrganizationDto;
import org.mjulikelion.likelion12thhw.week3.dto.response.Organization.GetUsersDto;
import org.mjulikelion.likelion12thhw.week3.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> registerOrganization(@RequestBody RegisterOrganizationDto registerOrganizationDto) {
        organizationService.register(registerOrganizationDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "단체 생성 완료"), HttpStatus.CREATED);
    }


    @PostMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<Void>> joinOrganization(
            @RequestHeader("userId") UUID userId, @PathVariable UUID organizationId) {
        organizationService.join(userId, organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 가입 완료"), HttpStatus.OK);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<Void>> withdrawOrganization(
            @RequestHeader("userId") UUID userId, @PathVariable UUID organizationId) {
        organizationService.withdraw(userId, organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 탈퇴 완료"), HttpStatus.OK);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<ResponseDto<GetUsersDto>> getOrganizationUsers(@PathVariable UUID organizationId) {
        GetUsersDto getUsersDto = organizationService.getUsers(organizationId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단체 소속 멤버 조회 성공", getUsersDto), HttpStatus.OK);
    }


}
