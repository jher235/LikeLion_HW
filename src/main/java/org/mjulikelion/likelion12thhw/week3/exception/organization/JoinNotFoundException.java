package org.mjulikelion.likelion12thhw.week3.exception.organization;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class JoinNotFoundException extends CustomException {
    public JoinNotFoundException() {
        super(ErrorCode.JOIN_NOT_FOUND);
    }

    public JoinNotFoundException(String detail) {
        super(ErrorCode.JOIN_NOT_FOUND, detail);
    }
}
