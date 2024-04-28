package org.mjulikelion.liikelion12thhw.week3;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Memo {

    private final int memoId;
    private final String content;
    @JsonIgnore //writerId는 반환이 안됨
    private final String writerId;
    private final List<User> likedList = new LinkedList<>();


}
