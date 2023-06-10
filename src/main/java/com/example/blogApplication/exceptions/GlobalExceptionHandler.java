package com.example.blogApplication.exceptions;

import com.example.blogApplication.payload.ResponseDTO;
import org.hibernate.validator.internal.properties.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RohanException.class)
    public ResponseEntity<?> returnException(RohanException rohanException){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setData(null);
            responseDTO.setMessage(rohanException.getMessage());
            responseDTO.setHttpStatus(HttpStatus.NOT_FOUND);
            responseDTO.setSuccess(false);

            return ResponseEntity.status(responseDTO.getHttpStatus()).body(responseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> returnRequestValidation(MethodArgumentNotValidException ex){

        Map<String,String> mp = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            mp.put(((FieldError)error).getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mp);

    }


}
