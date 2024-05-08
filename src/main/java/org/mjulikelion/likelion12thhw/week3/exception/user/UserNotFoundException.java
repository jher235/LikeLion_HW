package org.mjulikelion.likelion12thhw.week3.exception.user;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

    public UserNotFoundException(String detail) {
        super(ErrorCode.USER_NOT_FOUND, detail);
    }
}
