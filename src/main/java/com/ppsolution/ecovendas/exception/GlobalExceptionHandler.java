package com.ppsolution.ecovendas.exception;

import com.ppsolution.ecovendas.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        var status = HttpStatus.NOT_FOUND;
        var error = new ErrorResponse(
                status.getReasonPhrase(),
                e.getLocalizedMessage(),
                status.value(),
                e.getClass().getSimpleName(),
                LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyInUseException(ResourceAlreadyInUseException e){
        var status = HttpStatus.BAD_REQUEST;
        var error = new ErrorResponse(
                status.getReasonPhrase(),
                e.getLocalizedMessage(),
                status.value(),
                e.getClass().getSimpleName(),
                LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}
