package org.mjulikelion.likelion12thhw.config;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thhw.annotation.resolver.TestArgumentResolver;
import org.mjulikelion.likelion12thhw.test.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class TestConfig implements WebMvcConfigurer {
    private final TestInterceptor testInterceptor;
    private final TestArgumentResolver testArguementResolver;

    //이걸 통해 인터셉터를 등록한다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        동작하지 않도록 잠시 주석처리
//        registry.addInterceptor(testInterceptor)
//                .addPathPatterns("/memos/**");//어떤 URI를 통해서 인터셉터를 적용할지
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(testArguementResolver);
    }

}
