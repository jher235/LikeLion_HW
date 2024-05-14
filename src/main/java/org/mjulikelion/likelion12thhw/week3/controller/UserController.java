package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.annotation.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.authentication.JwtEncoder;
import org.mjulikelion.likelion12thhw.authentication.JwtTokenProvider;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.ModifyUserDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.RegisterUserDto;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Void>> registerUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        userService.registerUser(registerUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "유저 회원가입 성공"), HttpStatus.CREATED);
    }

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

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UUID>> login(@RequestBody @Valid LoginUserDto loginUserDto, HttpServletResponse response) {
        UUID id = userService.login(loginUserDto);

        String payload = id.toString();
        log.info(payload);
        String accessToken = jwtTokenProvider.createToken(payload);
        log.info(accessToken);
        ResponseCookie cookie = ResponseCookie.from("AccessToken", JwtEncoder.encodeJwtBearerToken(accessToken))
                .maxAge(Duration.ofSeconds(60 * 30))//30분
                .path("/")//모든 경로에서 접근가능
                .build();

        response.addHeader("set-cookie", cookie.toString());
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그인 성공", id), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDto<Void>> test(@AuthenticatedUser User user) {
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, user.getName()), HttpStatus.OK);
    }

}
