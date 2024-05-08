package org.mjulikelion.likelion12thhw.week3.exception.memo;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class MemoConflictException extends CustomException {
    public MemoConflictException() {
        super(ErrorCode.MEMO_CONFLICT);
    }

    public MemoConflictException(String detail) {
        super(ErrorCode.MEMO_CONFLICT, detail);
    }
}
