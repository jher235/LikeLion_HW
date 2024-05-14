package org.mjulikelion.likelion12thhw.annotation.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mjulikelion.likelion12thhw.annotation.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12thhw.authentication.AuthenticationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final AuthenticationContext authenticationContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info(authenticationContext.getPrincipal().getName());
        return authenticationContext.getPrincipal();//미리 추출해서 저장해놓은 유저 전달
    }
}
