package com.substring.foodie.dto_with_respect_to_entity;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ErrorResponse {
    private String message;
    private HttpStatus status;
}
