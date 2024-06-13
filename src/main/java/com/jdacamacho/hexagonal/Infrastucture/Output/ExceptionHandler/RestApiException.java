package com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import  com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.ExceptionStructure.Error;

import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;
import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorUtils;
import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.OwnException.BusinessRuleException;
import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.OwnException.EntityExistsException;
import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.OwnException.EntityNotFoundException;
import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.OwnException.NoDataException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestApiException {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final Exception ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.GENERIC_ERROR.getCode(),
                                        ErrorCode.GENERIC_ERROR.getMessageKey(),
                                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final EntityExistsException ex) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.ENTITY_EXISTS.getCode(),
                                        String.format("%s, %s", ErrorCode.ENTITY_EXISTS.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_ACCEPTABLE.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Error> handleNoDataException(final HttpServletRequest req,
                    final NoDataException ex) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.NO_DATA.getCode(),
                                        String.format("%s, %s", ErrorCode.NO_DATA.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_ACCEPTABLE.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(final HttpServletRequest req,
                    final EntityNotFoundException ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                                        String.format("%s, %s",
                                        ErrorCode.ENTITY_NOT_FOUND.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_FOUND.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Error> handleBusinessRuleException(final HttpServletRequest req,
                    final BusinessRuleException ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(),
                                        String.format("%s, %s",
                                        ErrorCode.BUSINESS_RULE_VIOLATION.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_ACCEPTABLE.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
                String campo = ((FieldError) error).getField();
                String mensajeDeError = error.getDefaultMessage();
                errores.put(campo, mensajeDeError);
        });

        return new ResponseEntity<Map<String, String>>(errores, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(),
                        HttpStatus.BAD_REQUEST);
    }
}
