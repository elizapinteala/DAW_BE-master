package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.PersonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PersonExceptionHandler {

    @ExceptionHandler(PersonException.class)
    public final ResponseEntity<String> handlePersonException(PersonException exception, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception.getError() == PersonException.PersonErrors.PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS){
            status = HttpStatus.FORBIDDEN;
        }
        else if(exception.getError() == PersonException.PersonErrors.PERSON_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        else if(exception.getError() == PersonException.PersonErrors.PERSON_COULD_NOT_BE_REMOVED){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<String>(exception.getError().name(), status);
    }
}
