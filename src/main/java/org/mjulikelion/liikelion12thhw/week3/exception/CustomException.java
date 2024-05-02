package org.mjulikelion.liikelion12thhw.week3.exception;

import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.errorcode.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detail;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.detail = null;
    }

    public CustomException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
