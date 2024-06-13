package com.operations.catoperations.exceptions;

public enum SuccessCode {
    CO_SUCCESS_1("CO_SUCCESS_1", "User Registered Successfully"),
    CO_SUCCESS_2("CO_SUCCESS_2", "User Logged In Successfully"),
    CO_SUCCESS_3("CO_SUCCESS_3", "All Users Fetched Successfully"),
    CO_SUCCESS_4("CO_SUCCESS_4", "Cat Image Uploaded Successfully"),
    CO_SUCCESS_5("CO_SUCCESS_5", "Fetched Cat Images Successfully"),
    CO_SUCCESS_6("CO_SUCCESS_6", "Deleted Cat Image Successfully");

    private final String code;
    private final String message;

    SuccessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return code;
    }
}
