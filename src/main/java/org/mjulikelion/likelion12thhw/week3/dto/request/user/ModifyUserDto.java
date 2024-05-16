package org.mjulikelion.likelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ModifyUserDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Email(message = "올바르지 않은 이메일 형식입니다.")
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 6, max = 20, message = "비밀번호는 6글자 이상, 20글자 이하여야 합니다.")
    private String password;

}
