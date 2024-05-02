package org.mjulikelion.liikelion12thhw.week3.exception;

import org.mjulikelion.liikelion12thhw.week3.errorcode.ErrorCode;

public class DtoValidationException extends CustomException {
    //에러코드 + 왜 실패했는지 저장하기 위한 detail
    public DtoValidationException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
