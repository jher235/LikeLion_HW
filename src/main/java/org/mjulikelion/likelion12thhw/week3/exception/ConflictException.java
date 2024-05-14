package org.mjulikelion.likelion12thhw.week3.exception;

import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ConflictException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
