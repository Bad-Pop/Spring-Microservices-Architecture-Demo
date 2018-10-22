package fr.alexisvachard.microservices.filmservice.web.v1.response;

public class ApiResponseV1<T> {

    private T body;
    private boolean succes;
    private int status;
    private String message;

    public ApiResponseV1() {
    }

    public ApiResponseV1(boolean succes, int status, String message) {
        this.succes = succes;
        this.status = status;
        this.message = message;
    }

    public ApiResponseV1(T body, boolean succes, int status) {
        this.body = body;
        this.succes = succes;
        this.status = status;
    }

    public ApiResponseV1(T body, boolean succes, int status, String message) {
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
