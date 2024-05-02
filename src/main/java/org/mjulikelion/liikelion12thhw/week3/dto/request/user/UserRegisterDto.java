package org.mjulikelion.liikelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserRegisterDto {
    @NotNull(message = "userId가 null입니다")
    private String userId;

    @NotNull(message = "name이 null입니다")
    @NotBlank(message = "이름은 공백일 수 없습니다")
    //message=를 따로 설정하지 않는 경우 Spring framework에서 기본 제공하는 값이 들어간다 -> "detail": "공백일 수 없습니다"
    @Length(max = 15, message = "name은 15글자 이하여야 합니다.")
    private String name;
}
//{
//        "errorCode": "9002",
//        "message": "필수값이 빈 값 혹은 공백입니다.",
//        "detail": "공백일 수 없습니다"
//        }