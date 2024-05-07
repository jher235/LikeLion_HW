package org.mjulikelion.likelion12thhw.week3.dto.request.memo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemoModifyDto {

    @NotNull(message = "내용을 작성해주세요")
    private String content;

    @NotNull(message = "제목을 작성해주세요")
    private String title;
}
