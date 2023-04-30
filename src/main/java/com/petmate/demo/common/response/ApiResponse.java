package com.petmate.demo.common.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
