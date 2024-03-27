package org.example.idflab.handler;

import org.example.idflab.exception.ParseExternalApiDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * handler исключений
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ParseExternalApiDataException.class)
    public ResponseEntity<String> handleParseExternalApiDataException(ParseExternalApiDataException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Невозможно прочитать JSON. Проверьте правильность формата.");
    }
}
