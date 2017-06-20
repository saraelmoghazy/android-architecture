package com.example.android.architecture.blueprints.todoapp.poc.data.remote;

/**
 * Created by user on 6/15/2017.
 */

public class ApiError {

    private int statusCode;
    private String message;

    public ApiError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
