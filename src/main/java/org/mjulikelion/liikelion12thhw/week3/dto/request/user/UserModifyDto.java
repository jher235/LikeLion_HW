package org.mjulikelion.liikelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserModifyDto {
    @NotNull(message = "이름을 입력해주세요")
    private String name;
}
