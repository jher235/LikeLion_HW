package org.mjulikelion.likelion12thhw.week3.exception;

import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class ForbiddenException extends CustomException {

    public ForbiddenException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
