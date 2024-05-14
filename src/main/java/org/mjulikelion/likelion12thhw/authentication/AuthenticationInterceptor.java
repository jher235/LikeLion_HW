package org.mjulikelion.likelion12thhw.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thhw.week3.exception.NotFoundException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationContext authenticationContext;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {

        String accessToken = AuthenticationExtractor.extract(request);
        UUID userId = UUID.fromString(jwtTokenProvider.getPayload(accessToken)); //accessToken으로 payload추출
        User user = findUserByUserId(userId);
        authenticationContext.setPrincipal(user);//컨텍스트에 유저 정보 저장
        return true;
    }


    private User findUserByUserId(final UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
