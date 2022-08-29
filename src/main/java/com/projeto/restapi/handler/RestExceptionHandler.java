package com.projeto.restapi.handler;

import com.projeto.restapi.exception.InvalidToken;
import com.projeto.restapi.exception.NegocioException;
import com.projeto.restapi.exception.ResourceExceptionDetails;
import com.projeto.restapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ResourceExceptionDetails rfnDetails = ResourceExceptionDetails
                .Builder
                .aResourceNotFoundDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Não encontrado")
                .detail(resourceNotFoundException.getMessage())
                .developerMessage(resourceNotFoundException.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleResourceNegocioException(NegocioException resourceNotFoundException){
        ResourceExceptionDetails rfnDetails = ResourceExceptionDetails
                .Builder
                .aResourceNotFoundDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro de negócio.")
                .detail(resourceNotFoundException.getMessage())
                .developerMessage(resourceNotFoundException.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<?> handleResourceTokenException(InvalidToken invalidToken){
        ResourceExceptionDetails rfnDetails = ResourceExceptionDetails
                .Builder
                .aResourceNotFoundDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .title("Erro de Token.")
                .detail(invalidToken.getMessage())
                .developerMessage(invalidToken.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleResourceException(NegocioException resourceNotFoundException){
        ResourceExceptionDetails rfnDetails = ResourceExceptionDetails
                .Builder
                .aResourceNotFoundDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro de negócio.")
                .detail(resourceNotFoundException.getMessage())
                .developerMessage(resourceNotFoundException.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }



}
