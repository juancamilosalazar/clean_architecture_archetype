package co.com.banco.model.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ValidationException extends RuntimeException {

    private final String statusCode;
    private final String technicalDetail;

    public ValidationException(String statusCode, String message, String technicalDetail) {
        super( message );
        this.statusCode = statusCode;
        this.technicalDetail = technicalDetail;
    }
}
