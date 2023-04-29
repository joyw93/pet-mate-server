package com.petmate.demo.base;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private Object data;
}
