package org.mjulikelion.likelion12thhw.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//이 타겟에서 어노테이션이 어떤 곳에서 사용될지를 명시 -> 이 경우엔 파라미터에서 사용할 것
@Retention(RetentionPolicy.RUNTIME)//이 어노테이션이 언제까지 유지될 것인가를 정의
public @interface Test {

}
