package org.mjulikelion.likelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegisterUserDto {

    @NotBlank(message = "이름을 입력해주세요")
    @Length(max = 15, message = "이름은 15글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "email은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Length(max = 20, message = "비밀번호는 20글자 이하여야 합니다.")
    private String password;
}
