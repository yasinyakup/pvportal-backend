package com.kalyon.pvportalbackend.controller.handler;

import com.kalyon.pvportalbackend.dto.response.MessageResponse;
import com.kalyon.pvportalbackend.exception.NotNullException;
import com.kalyon.pvportalbackend.exception.ResourceAlreadyExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<MessageResponse> resorceAlreadyExistHandler(ResourceAlreadyExist resourceAlreadyExist){
        return ResponseEntity.badRequest()
                .body(new MessageResponse(new Date(), resourceAlreadyExist.getMessage()));
    }


    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<MessageResponse> notNullHandler(NotNullException notNullException){
        return ResponseEntity.badRequest()
                .body(new MessageResponse(new Date(), notNullException.getMessage()));
    }
/*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> defaultHandler(Exception exception) {

        return ResponseEntity.badRequest()
                .body(new MessageResponse(new Date(), exception.getMessage()));
    }
 */
}
