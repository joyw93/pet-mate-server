package com.petmate.demo.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    private Object errors;


    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(true, message, data, null);
    }

    public static ApiResponse failure(String message, Object errors) {
        return new ApiResponse(false, message, null, errors);
    }
}
