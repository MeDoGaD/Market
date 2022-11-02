package cis.springboot.errors;

import org.springframework.http.HttpStatus;

public abstract class APIBaseException extends RuntimeException{
    public APIBaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
   public abstract HttpStatus getStatusCode();
}
