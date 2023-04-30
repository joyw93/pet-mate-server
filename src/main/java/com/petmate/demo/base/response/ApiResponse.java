package com.petmate.demo.base.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
