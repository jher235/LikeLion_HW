package org.mjulikelion.likelion12thhw.week3.exception;

import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class AuthorizeException extends CustomException {
    public AuthorizeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthorizeException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
