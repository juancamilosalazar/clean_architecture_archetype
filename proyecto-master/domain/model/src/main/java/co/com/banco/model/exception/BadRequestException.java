package co.com.banco.model.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BadRequestException extends RuntimeException{

    private final String statusCode;
    private final String technicalDetail;

    public BadRequestException(String statusCode, String message, String technicalDetail) {
        super( message );
        this.statusCode = statusCode;
        this.technicalDetail = technicalDetail;
    }
}
