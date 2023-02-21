package com.example.springcloudlocalstack.exception;

import com.example.springcloudlocalstack.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SaveFileException.class)
    public ErrorDTO handle(SaveFileException exception) {
        ErrorDTO error = new ErrorDTO(exception.getMessage());
        return error;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GetFileException.class)
    public ErrorDTO handle(GetFileException exception) {
        ErrorDTO error = new ErrorDTO(exception.getMessage());
        return error;
    }
}