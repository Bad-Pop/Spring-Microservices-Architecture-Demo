package fr.alexisvachard.microservices.filmservice.web.response;

public class ApiResponse<T> {

    private T body;
    private boolean succes;
    private int status;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(T body, boolean succes, int status, String message) {
        this.body = body;
        this.succes = succes;
        this.status = status;
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
