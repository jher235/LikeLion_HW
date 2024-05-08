package org.mjulikelion.likelion12thhw.week3.exception.memo;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class MemoNotFoundException extends CustomException {
    public MemoNotFoundException() {
        super(ErrorCode.MEM0_NOT_FOUND);
    }

    public MemoNotFoundException(String detail) {
        super(ErrorCode.MEM0_NOT_FOUND, detail);
    }
}
