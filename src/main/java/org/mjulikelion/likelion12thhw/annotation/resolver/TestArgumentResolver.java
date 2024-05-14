package org.mjulikelion.likelion12thhw.annotation.resolver;

import org.mjulikelion.likelion12thhw.annotation.test.Test;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TestArgumentResolver implements HandlerMethodArgumentResolver {
    //이 메소드 파라미터가 파라미터 어노테이션을 갖도록 지원함
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Test.class);//파라미터 어노테이션을 갖도록 지원함
    }

    @Override
    public String resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        return "TestArgumentResolver";
    }
}
