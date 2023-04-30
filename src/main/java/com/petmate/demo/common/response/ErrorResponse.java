package com.petmate.demo.common.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private boolean success;
    private String message;
    private Object errors;

    public ErrorResponse(String message, Object errors) {
        this.success = false;
        this.message = message;
        this.errors = errors;
    }
}