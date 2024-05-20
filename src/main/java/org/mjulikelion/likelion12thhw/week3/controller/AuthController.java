package org.mjulikelion.likelion12thhw.week3.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thhw.week3.annotation.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.week3.authentication.JwtEncoder;
import org.mjulikelion.likelion12thhw.week3.authentication.JwtTokenProvider;
import org.mjulikelion.likelion12thhw.week3.dto.ResponseDto;
import org.mjulikelion.likelion12thhw.week3.dto.request.user.LoginUserDto;
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
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Void>> registerUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        userService.register(registerUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "유저 회원가입 성공"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody @Valid LoginUserDto loginUserDto, HttpServletResponse response) {
        UUID id = userService.login(loginUserDto);

        String payload = id.toString();
        String accessToken = jwtTokenProvider.createToken(payload);
        ResponseCookie cookie = ResponseCookie.from("AccessToken", JwtEncoder.encodeJwtBearerToken(accessToken))
                .maxAge(Duration.ofSeconds(60 * 30))//30분
                .path("/")//모든 경로에서 접근가능
                .build();

        response.addHeader("set-cookie", cookie.toString());
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그인 성공"), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<Void>> logout(HttpServletResponse response) {
        expireCookie(response, "AccessToken");
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그아웃 성공"), HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<ResponseDto<Void>> test(@AuthenticatedUser User user) {
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, user.getName()), HttpStatus.OK);
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "null");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
