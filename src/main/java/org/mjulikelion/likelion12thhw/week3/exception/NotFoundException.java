package org.mjulikelion.likelion12thhw.week3.exception;

import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(String detail) {
        super(ErrorCode.NOT_FOUND, detail);
    }
}
