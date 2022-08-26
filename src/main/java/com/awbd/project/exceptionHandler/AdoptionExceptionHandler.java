package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.AdoptionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AdoptionExceptionHandler {

    @ExceptionHandler(AdoptionException.class)
    public final ResponseEntity<String> handleAdoptionException(AdoptionException e, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(e.getErrors() == AdoptionException.AdoptionErrors.INVALID_ADOPTION_DATE){
            status = HttpStatus.FORBIDDEN;
        }
        else if(e.getErrors() == AdoptionException.AdoptionErrors.ANIMAL_IS_UNDER_TREATMENT_AND_CANT_BE_ADOPTED_AT_THE_MOMENT){
            status = HttpStatus.UNAUTHORIZED;
        }
        else if(e.getErrors() == AdoptionException.AdoptionErrors.ANIMAL_IS_ALREADY_ADOPTED){
            status = HttpStatus.UNAUTHORIZED;
        }
        else if(e.getErrors() == AdoptionException.AdoptionErrors.ADOPTION_COULD_NOT_BE_SAVED){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(e.getErrors() == AdoptionException.AdoptionErrors.ADOPTION_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<String>(e.getErrors().name(),status);
    }
}
