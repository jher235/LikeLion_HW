package org.mjulikelion.liikelion12thhw.week3.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserModifyDto {
    @NotNull(message = "name이 null입니다")
    private String name;
}
