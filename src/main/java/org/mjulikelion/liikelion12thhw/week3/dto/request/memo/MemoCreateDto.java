package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

//@AllArgsConstructor //들어가면 오류가 발생
@Getter
public class MemoCreateDto {

    @NotNull(message = "content가 null입니다")
    private String content;

    @NotNull(message = "title이 null입니다")
    private String title;
}
