package org.mjulikelion.liikelion12thhw.week3.dto.request.like;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterLikeDto {
//    @NotNull(message = "userId가 없습니다.")
//    private String userId;

    @NotNull(message = "userName이 없습니다")
    private String userName;
}
