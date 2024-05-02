package org.mjulikelion.liikelion12thhw.week3.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserModifyDto;
import org.mjulikelion.liikelion12thhw.week3.dto.request.user.UserRegisterDto;
import org.mjulikelion.liikelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    //    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public void registerUser(@RequestBody UserRegisterDto userRegisterDto) {
//        userService.registerUser(userRegisterDto);
//    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Null>> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "유저 회원가입 성공"), HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<ResponseDto<Null>> modifyUser(@RequestHeader String userId, @RequestBody @Valid UserModifyDto userModifyDto) {
        userService.modify(userModifyDto, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 수정 성공"), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto<Null>> deleteUser(@RequestHeader String userId) {
        userService.delete(userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "유저 정보 삭제 성공"), HttpStatus.CREATED);
    }


}
