package cis.springboot.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(APIBaseException.class)
    public ResponseEntity<ErrorMessage>handleExceptions(APIBaseException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError=new ValidationError();
        validationError.setUri(request.getDescription(false));
        List<FieldError>errors=ex.getBindingResult().getFieldErrors();
        for(FieldError f:errors){
            validationError.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }
}
