package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.AnimalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AnimalExceptionHandler {

    @ExceptionHandler(AnimalException.class)
    public final ResponseEntity<String> handleAnimalException(AnimalException exception, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception.getErrors() == AnimalException.AnimalErrors.ANIMAL_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<String>(exception.getErrors().name(), status);
    }
}
