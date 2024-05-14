package org.mjulikelion.likelion12thhw.test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

@Slf4j
//@AllArgsConstructor
@Component
@RequiredArgsConstructor
public class TestInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("userId");
        log.info(userId);

        if (!userRepository.existsById(UUID.fromString(userId))) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("TestInterceptor preHandle");
        return true;//true면 컨트롤러에 전달
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        log.info("TestInterceptor postHandle");
        log.info("response status: {}", response.getStatus());
        try {
            response.getOutputStream().print("TestInterceptor postHandle");
        } catch (IOException e) {
            log.error("error");
        }
    }


}

