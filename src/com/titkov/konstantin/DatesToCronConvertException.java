package com.titkov.konstantin;

public class DatesToCronConvertException extends Exception {

    String message;
    Exception cause;


    public DatesToCronConvertException(Exception e) {
        this.cause = e;
    }

    public DatesToCronConvertException(String e) {
        this.message = e;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
