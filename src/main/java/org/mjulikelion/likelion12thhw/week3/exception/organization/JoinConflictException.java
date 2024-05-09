package org.mjulikelion.likelion12thhw.week3.exception.organization;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class JoinConflictException extends CustomException {
    public JoinConflictException() {
        super(ErrorCode.JOIN_CONFLICT);
    }

    public JoinConflictException(String detail) {
        super(ErrorCode.JOIN_CONFLICT, detail);
    }
}
