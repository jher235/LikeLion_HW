package org.mjulikelion.likelion12thhw.week3.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("4040", "유저를 찾을 수 없습니다."),
    MEM0_NOT_FOUND("4041", "메모를 찾을 수 없습니다."),
    ORGANIZATION_NOT_FOUND("4042", "단체를 찾을 수 없습니다."),
    USER_CONFLICT("4090", "이미 존재하는 유저입니다."),
    MEMO_CONFLICT("4091", "이미 존재하는 메모입니다."),
    ORGANIZATION_CONFLICT("4092", "이미 존재하는 단체입니다."),
    FORBIDDEN("4030", "접근 권한이 없습니다"),

    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 공백입니다."),
    REGEX("9003", "형식에 맞지 않습니다."),
    LENGTH("9004", "길이가 유효하지 않습니다"),
    SIZE("9005", "길이가 유효하지 않습니다"),
    EMAIL("9006", "잘못된 이메일 형식입니다");


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
