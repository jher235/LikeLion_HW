package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.annotation.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PatchMapping()
    public ResponseEntity<ResponseDto<Void>> modifyUser(@AuthenticatedUser User user, @RequestBody @Valid ModifyUserDto modifyUserDto) {
        userService.modify(modifyUserDto, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 수정 성공"), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto<Void>> deleteUser(@AuthenticatedUser User user) {
        userService.delete(user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 삭제 성공"), HttpStatus.CREATED);
    }


}
