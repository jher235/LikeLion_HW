package org.mjulikelion.liikelion12thhw.week3.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ResponseDto<T> { //ResponseBody에 넣을 데이터

    private final String statusCode;// 상태 코드
    private final String message;//API 요청 결과
    private final T data;//API 요청 결과 데이터

    //반환 데이터가 없는 API를 위한 응답
    public static <T> ResponseDto<T> res(final HttpStatusCode statusCode, final String message) {
        return new ResponseDto<>(String.valueOf(statusCode), message, null);
    }

    //반환 데이터가 있는 API를 위한 응답
    public static <T> ResponseDto<T> res(final HttpStatusCode statusCode, final String message, final T data) {
        return new ResponseDto<>(String.valueOf(statusCode), message, data);
    }

}
