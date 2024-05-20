package org.mjulikelion.likelion12thhw.week3.dto.request.like;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterLikeDto {
    @NotBlank(message = "이름은 필수 항목입니다.")
    private String userName;
}
