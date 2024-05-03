package org.mjulikelion.liikelion12thhw.week3.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.liikelion12thhw.week3.dto.ErrorResponseDto;
import org.mjulikelion.liikelion12thhw.week3.exception.CustomException;
import org.mjulikelion.liikelion12thhw.week3.exception.DtoValidationException;
import org.mjulikelion.liikelion12thhw.week3.exception.errorcode.ErrorCode;
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

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException notFoundException) {
//        this.writeLog(notFoundException);
//        return new ResponseEntity<>(ErrorResponseDto.res(notFoundException), HttpStatus.NOT_FOUND);
//    }

    //예상 가능할 때 오류 - 다형성으로 한번에 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException customException) {
        this.writeLog(customException);
        HttpStatus httpStatus = this.resolveHttpStatus(customException);//customException의 HttpStatus를 반환 받아옴
        return new ResponseEntity<>(ErrorResponseDto.res(customException), httpStatus);
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
        if (fieldError == null) {
            return new ResponseEntity<>(ErrorResponseDto.res(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
        }
        //실패한 유효성 검증에 대한 에러 코드 가져옴
        ErrorCode validationErrorCode = ErrorCode.resolveValidationErrorCode(fieldError.getCode());
        String detail = fieldError.getDefaultMessage();
        DtoValidationException dtoValidationException = new DtoValidationException(validationErrorCode, detail);
        this.writeLog(dtoValidationException);
        return new ResponseEntity<>(ErrorResponseDto.res(dtoValidationException), HttpStatus.BAD_REQUEST);
    }

    private HttpStatus resolveHttpStatus(CustomException customException) {
        return HttpStatus.resolve(Integer.parseInt(customException.getErrorCode().getCode().substring(0, 3)));
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
