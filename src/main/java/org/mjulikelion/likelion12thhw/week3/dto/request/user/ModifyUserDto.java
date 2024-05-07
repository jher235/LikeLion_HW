package org.mjulikelion.likelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ModifyUserDto {
    @NotNull(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

}
