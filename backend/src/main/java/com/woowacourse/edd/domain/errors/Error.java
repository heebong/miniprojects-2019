package com.woowacourse.edd.domain.errors;

public class Error {
    private String result;
    private String message;

    private Error() {
    }

    public Error(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
