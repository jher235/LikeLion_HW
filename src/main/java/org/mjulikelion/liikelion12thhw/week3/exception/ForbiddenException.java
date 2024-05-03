package org.mjulikelion.liikelion12thhw.week3.exception;

import org.mjulikelion.liikelion12thhw.week3.exception.errorcode.ErrorCode;

public class ForbiddenException extends CustomException {
    public ForbiddenException(String detail) {
        super(ErrorCode.FORBIDDEN, detail);
    }
}
