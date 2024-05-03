package org.mjulikelion.liikelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserRegisterDto {

    @NotBlank(message = "name은 공백일 수 없습니다")
    @Length(max = 15, message = "name은 15글자 이하여야 합니다.")
    private String name;
}
