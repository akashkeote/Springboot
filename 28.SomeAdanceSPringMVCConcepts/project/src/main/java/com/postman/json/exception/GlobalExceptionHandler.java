package com.postman.json.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//@ControllerAdvice
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public String handleAkNullPointerString(NullPointerException e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException e) {
        List<ObjectError> Allerrors = e.getBindingResult().getAllErrors();
        Map<String, String> errorMap = new HashMap<>();

        Allerrors.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });

        logger.error(e.getMessage());
        e.printStackTrace();
        return errorMap.toString();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException1(MethodArgumentTypeMismatchException e) {
        String paramName = e.getName();
        Object invalidValue = e.getValue();
        String expectedType = e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "unknown";

        logger.error("Type mismatch for parameter '{}': value '{}' is not valid for type {}", paramName, invalidValue,
                expectedType);
        e.printStackTrace();
        return "Type mismatch for parameter '" + paramName + "': value '" + invalidValue + "' is not valid for type "
                + expectedType;
    }
}
