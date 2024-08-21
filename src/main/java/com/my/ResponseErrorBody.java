package com.my;

public class ResponseErrorBody {
    private String message;

    public ResponseErrorBody(String message) {
        this.message = message;
    }


    public ResponseErrorBody() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
