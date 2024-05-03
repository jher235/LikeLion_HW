package org.mjulikelion.liikelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserRegisterDto {

    @NotBlank(message = "이름을 입력해주세요")
    @Length(max = 15, message = "이름은 15글자 이하여야 합니다.")
    private String name;
}
