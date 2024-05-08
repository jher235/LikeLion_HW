package org.mjulikelion.likelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterUserDto {

    @NotBlank(message = "이름을 입력해주세요")
    @Size(min = 2, max = 15, message = "이름은 2글자이상, 15글자 이하여야 합니다.")
    private String name;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "email은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6글자 이상, 20글자 이하여야 합니다.")
    private String password;
}
