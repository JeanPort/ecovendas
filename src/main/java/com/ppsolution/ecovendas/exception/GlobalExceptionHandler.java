package com.ppsolution.ecovendas.exception;

import com.ppsolution.ecovendas.dto.response.ErrorResponse;
import com.ppsolution.ecovendas.dto.response.ErrorValidateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

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

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<ErrorResponse> handlePasswordValidationException(PasswordValidationException e){
        var status = HttpStatus.BAD_REQUEST;
        var error = new ErrorResponse(
                status.getReasonPhrase(),
                e.getLocalizedMessage(),
                status.value(),
                e.getClass().getSimpleName(),
                LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidateResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var status = HttpStatus.BAD_REQUEST;
        var errors = new HashMap<String, List<String>>();
        var fieldErrors = e.getBindingResult().getFieldErrors();
        var message = "Erro de validação";

        setErrors(fieldErrors, errors);

        var error = new ErrorValidateResponse(
                status.getReasonPhrase(),
                message,
                status.value(),
                e.getClass().getSimpleName(),
                LocalDateTime.now(),
                errors
                );
        return ResponseEntity.status(status).body(error);
    }

    private static void setErrors(List<FieldError> fieldErrors, HashMap<String, List<String>> errors) {
        fieldErrors.forEach(fieldError -> {
            var fieldName = fieldError.getField();
            if (errors.containsKey(fieldName)){
                errors.get(fieldName).add(fieldError.getDefaultMessage());
            }else {
                var listMessages = new ArrayList<>(List.of(Objects.requireNonNull(fieldError.getDefaultMessage())));
                errors.put(fieldName, listMessages);
            }
        });
    }
}
