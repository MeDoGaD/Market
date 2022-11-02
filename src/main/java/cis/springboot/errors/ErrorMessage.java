package cis.springboot.errors;

public class ErrorMessage {
    private String message;
    private String uri;

    public ErrorMessage(String message, String uri) {
        this.message = message;
        this.uri = uri;
    }

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
