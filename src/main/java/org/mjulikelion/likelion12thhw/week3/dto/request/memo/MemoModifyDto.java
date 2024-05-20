package org.mjulikelion.likelion12thhw.week3.dto.request.memo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemoModifyDto {

    @NotBlank(message = "내용을 작성해주세요")
    private String content;

    @NotBlank(message = "제목을 작성해주세요")
    private String title;
}
