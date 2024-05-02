package org.mjulikelion.liikelion12thhw.week3.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Memo {

    private final int memoId;
    @NotBlank(message = "title은 빈칸일 수 없습니다.")
    private final String title;
    private final String content;
    @JsonIgnore //writerId는 반환이 안됨
    private final String writerId;
//    private final List<User> likedList = new LinkedList<>();


}
