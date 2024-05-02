package org.mjulikelion.liikelion12thhw.week3.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("4040", "유저를 찾을 수 없습니다."),
    MEMO_NOT_FOUND("4041", "메모를 찾을 수 없습니다."),

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
            case "Pattern" -> REGEX;
            case "Length" -> LENGTH;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
