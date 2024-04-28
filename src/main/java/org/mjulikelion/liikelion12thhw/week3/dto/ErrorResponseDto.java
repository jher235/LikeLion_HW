package org.mjulikelion.liikelion12thhw.week3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.liikelion12thhw.week3.exception.CustomException;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private final String errorCode;
    private final String message;
    private final String detail;

    //예상 가능한 예외일 ㄱㅇ우
    public static ErrorResponseDto res(final CustomException customException) {
        String errorCode = customException.getErrorCode().getCode();
        String message = customException.getErrorCode().getMessage();
        String detail = customException.getDetail();
        return new ErrorResponseDto(errorCode, message, detail);
    }

    //예상하지 못하는 예외일 경우
    public static ErrorResponseDto res(final String errorCode, final Exception exception) {
        return new ErrorResponseDto(errorCode, exception.getMessage(), null);
    }
}