package com.business.electr.clothes.net.exception;

public class ResponseException extends Exception {

    private String errorCode;
    private String errorMessage;

    public ResponseException(Throwable cause, String errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResponseException(Throwable cause, int errorCode, String errorMessage) {
        super(cause);
        this.errorCode = Integer.toString(errorCode);
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseException[errorCode: " + this.errorCode + ", errorMessage: " + errorMessage + "]";
    }
}
