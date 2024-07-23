/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lakhaninashitahomework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author NASHITA LAKHANI
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception mamte) {
        String result = "Exception: " + mamte.getMessage();
        logger.warn(mamte.getClass() + "");
        logger.warn(result);
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException manve) {
        logger.error("We have an exception: MethodArgumentNotValidException");
        ValidationErrorMessage message = new ValidationErrorMessage();
        //get the binding result
        BindingResult br = manve.getBindingResult();
        
        for (FieldError fe : br.getFieldErrors()) {
            logger.error(fe.getCode());
            logger.error(fe.getField());
            logger.error(fe.toString());
            logger.error(fe.getDefaultMessage());
 
            FieldErrorMessage fem = new FieldErrorMessage();
            fem.setField(fe.getField());
            fem.setMessage(fe.getDefaultMessage());
            message.addFieldErrorMessage(fem);
        }
            
        
        
        
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException hmnre) {
        String result = "HttpMessageNotReadableException: " + hmnre.getMessage();
         logger.warn(result);
         logger.warn(hmnre.getHttpInputMessage() + "");
        return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException mamte) {
        String result = "MethodArgumentTypeMismatchException: " + mamte.getName() + ":" + mamte.getParameter() + ":" + mamte.getMessage();
         logger.warn(result);
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
}
