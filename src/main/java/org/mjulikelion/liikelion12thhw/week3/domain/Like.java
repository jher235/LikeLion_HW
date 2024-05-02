package org.mjulikelion.liikelion12thhw.week3.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Like {
    private final int memoId;
    private final String userName;
    private final LocalDateTime date = LocalDateTime.now(); //원래 private LocalDateTime date으로 선언하려고 했는데 @RequiredArgsConstructor이 자꾸 final을 넣어준다.
}
