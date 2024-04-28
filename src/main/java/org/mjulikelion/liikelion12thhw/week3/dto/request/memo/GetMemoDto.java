package org.mjulikelion.liikelion12thhw.week3.dto.request.memo;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.Memo;

@Getter
public class GetMemoDto {

    //수정필요
    private final Memo memo;


    public GetMemoDto(Memo memo) {
        this.memo = memo;
    }
}
