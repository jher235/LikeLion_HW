package org.mjulikelion.liikelion12thhw.week3;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@Getter
@ToString
public class Memo {

    private final int mid;
    private final String content;
    private final String writerId;


}
