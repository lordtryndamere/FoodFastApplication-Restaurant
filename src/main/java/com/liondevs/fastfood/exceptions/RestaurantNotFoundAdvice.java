package com.liondevs.fastfood.exceptions;

import com.liondevs.fastfood.exceptions.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestaurantNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String,String> restaurantNotFoundHandler(RestaurantNotFoundException ex) {
        Map<String, String> bodyOfResponse = new HashMap<>();
        String httpStatusCode = Integer.toString(HttpStatus.NOT_FOUND.value());
        bodyOfResponse.put("message",ex.getMessage());
        bodyOfResponse.put("code",httpStatusCode);
        return bodyOfResponse;
    }
}
