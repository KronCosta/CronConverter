package com.titkov.konstantin;

public class DatesToCronConvertException extends Exception {

     final String message = "\" doesn't have 5 or 6 segments as excepted\"";




    public DatesToCronConvertException() {

    }

    @Override
    public String getMessage() {
        return message;
    }
}
