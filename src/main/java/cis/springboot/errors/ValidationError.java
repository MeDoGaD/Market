package cis.springboot.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    public List<String> errorMessages;
    private String uri;

    public ValidationError(List<String> errorMessages, String uri) {
        this.errorMessages = errorMessages;
        this.uri = uri;
    }

    public ValidationError() {
        errorMessages=new ArrayList();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public void addError(String error){
        errorMessages.add(error);
    }
}
