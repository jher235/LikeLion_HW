package org.mjulikelion.liikelion12thhw.week3.exception;

import org.mjulikelion.liikelion12thhw.week3.errorcode.ErrorCode;

public class MemoNotFoundException extends CustomException {
    public MemoNotFoundException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
