package org.mjulikelion.liikelion12thhw.week3.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND("4040", "데이터를 찾을 수 없습니다."),
    CONFLICT("4090", "이미 존재하는 데이터입니다."),
    FORBIDDEN("4030", "접근 권한이 없습니다"),

    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 공백입니다."),
    REGEX("9003", "형식에 맞지 않습니다."),
    LENGTH("9004", "길이가 유효하지 않습니다");


    private final String code;
    private final String message;

    //Dto의 유효성 검사를 실패한 경우, 에러코드 반환
    public static ErrorCode resolveValidationErrorCode(String code) {
        return switch (code) {
            case "NotNull" -> NOT_NULL;
            case "NotBlank" -> NOT_BLANK;
            case "Length" -> LENGTH;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
