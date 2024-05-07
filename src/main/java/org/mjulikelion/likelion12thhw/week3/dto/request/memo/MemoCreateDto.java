package org.mjulikelion.likelion12thhw.week3.dto.request.memo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

//@AllArgsConstructor //들어가면 오류가 발생
@Getter
public class MemoCreateDto {

    @NotNull(message = "내용을 작성해주세요")
    private String content;

    @NotNull(message = "제목을 작성해주세요")
    private String title;
}
