package org.mjulikelion.liikelion12thhw.week3.controller;

import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.liikelion12thhw.week3.dto.ErrorResponseDto;
import org.mjulikelion.liikelion12thhw.week3.errorcode.ErrorCode;
import org.mjulikelion.liikelion12thhw.week3.exception.CustomException;
import org.mjulikelion.liikelion12thhw.week3.exception.DtoValidationException;
import org.mjulikelion.liikelion12thhw.week3.exception.MemoNotFoundException;
import org.mjulikelion.liikelion12thhw.week3.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    //UserNotFoundException handler
    @ResponseStatus(HttpStatus.NOT_FOUND)//response HTTP 상태 코드 404(NOT_FOUND)로 설정
    @ExceptionHandler(UserNotFoundException.class)//정의해둔 UserNotFoundException의 핸들러
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        this.writeLog(userNotFoundException);
        return new ResponseEntity<>(ErrorResponseDto.res(userNotFoundException), HttpStatus.NOT_FOUND);//예외 처리 dto, HTTP response 반환
    }

    //MemoNotFoundException 핸들러
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleMemoNotFoundException(MemoNotFoundException memoNotFoundException) {
        this.writeLog(memoNotFoundException);
        return new ResponseEntity<>(ErrorResponseDto.res(memoNotFoundException), HttpStatus.NOT_FOUND);
    }

    //원인을 알 수 없는 예외처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)// 500(INTERNAL_SERVER_ERROR)로 설정
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        this.writeLog(exception);
        return new ResponseEntity<>(
                ErrorResponseDto.res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    //Dto 유효성 검사 실패한 경우의 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        //methodArgumentNotValidException의 BindingResult의 FieldError(유효성 검증 실패 필드)를 찾아서 저장
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        if (fieldError == null) {//만일 유효성 검증 실패한 필드가 없다면, 예외에 대한 응답 생성 후 반환
            return new ResponseEntity<>(ErrorResponseDto.res(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
        }
        //실패한 유효성 검증에 대한 에러 코드 가져옴
        ErrorCode validationErrorCode = ErrorCode.resolveValidationErrorCode(fieldError.getCode());
        String detail = fieldError.getDefaultMessage();//실패한 유효성 검증 상세설명
        DtoValidationException dtoValidationException = new DtoValidationException(validationErrorCode, detail); //가져온 에러코드, 디테일을 저장
        this.writeLog(dtoValidationException);
        return new ResponseEntity<>(ErrorResponseDto.res(dtoValidationException), HttpStatus.BAD_REQUEST);


    }

    //예측 가능 오류 로그 출력
    private void writeLog(CustomException customException) {
        String excptionName = customException.getClass().getSimpleName();
        ErrorCode errorCode = customException.getErrorCode();
        String detail = customException.getDetail();

        log.error("({}){}: {}", excptionName, errorCode.getMessage(), detail);
    }

    //예측 못한 오류 로그 출력
    private void writeLog(Exception exception) {
        log.error("({}){}: {}", exception.getClass().getSimpleName(), exception.getMessage());
    }

}
