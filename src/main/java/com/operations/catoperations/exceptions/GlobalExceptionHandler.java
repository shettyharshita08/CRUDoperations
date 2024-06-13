package com.operations.catoperations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorCode> handleApplicationException(ApplicationException applicationException, WebRequest webRequest) {
        ErrorCode errorCode = new ErrorCode();
        errorCode.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorCode.setMessage(applicationException.getMessage());
        return new ResponseEntity<>(errorCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorCode> handleAuthenticationException(AuthenticationException authenticationException, WebRequest webRequest) {
        ErrorCode errorCode = new ErrorCode();
        errorCode.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorCode.setMessage(authenticationException.getMessage());
        return new ResponseEntity<>(errorCode, HttpStatus.UNAUTHORIZED);
    }
}
