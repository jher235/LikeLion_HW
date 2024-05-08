package org.mjulikelion.likelion12thhw.week3.exception.user;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class UserConflictException extends CustomException {
    public UserConflictException() {
        super(ErrorCode.USER_CONFLICT);
    }

    public UserConflictException(String detail) {
        super(ErrorCode.USER_CONFLICT, detail);
    }
}
