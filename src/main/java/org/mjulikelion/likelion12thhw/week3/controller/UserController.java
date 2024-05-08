package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.RegisterUserDto;
import org.mjulikelion.likelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Void>> registerUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        userService.registerUser(registerUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "유저 회원가입 성공"), HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<ResponseDto<Void>> modifyUser(@RequestHeader("userId") UUID userId, @RequestBody @Valid ModifyUserDto modifyUserDto) {
        userService.modify(modifyUserDto, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 수정 성공"), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto<Void>> deleteUser(@RequestHeader("userId") UUID userId) {
        userService.delete(userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 삭제 성공"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UUID>> login(@RequestBody @Valid LoginUserDto loginUserDto) {
        UUID id = userService.login(loginUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그인 성공", id), HttpStatus.OK);
    }


}
