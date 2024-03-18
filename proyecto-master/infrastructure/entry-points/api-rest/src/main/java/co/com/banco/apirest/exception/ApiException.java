package co.com.banco.apirest.exception;

import co.com.banco.model.error.ErrorResponse;
import co.com.banco.model.exception.BadRequestException;
import co.com.banco.model.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiException {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException validationException) {

        return getResponseEntity(
                ErrorResponse.builder()
                        .statusCode(validationException.getStatusCode())
                        .message(validationException.getMessage())
                        .technicalDetail(validationException.getTechnicalDetail())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException) {

        return getResponseEntity(
                ErrorResponse.builder()
                        .statusCode(badRequestException.getStatusCode())
                        .message(badRequestException.getMessage())
                        .technicalDetail(badRequestException.getTechnicalDetail())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException
                                                                                    methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        return getResponseEntity(ErrorResponse.builder()
                .statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(MessageException.VALIDATION.concat(fieldErrors.get(0).getField()))
                .technicalDetail(fieldErrors.get(0).getDefaultMessage())
                .build(),HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> getResponseEntity(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
