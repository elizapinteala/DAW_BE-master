package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.ShelterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ShelterExceptionHandler {

    @ExceptionHandler(ShelterException.class)
    public final ResponseEntity<String> handleShelterException(ShelterException exception, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception.getErrors() == ShelterException.ShelterErrors.SHELTER_WITH_THE_SAME_ADDRESS_ALREADY_EXISTS){
            status = HttpStatus.FORBIDDEN;
        }
        else if(exception.getErrors() == ShelterException.ShelterErrors.SHELTER_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        else if(exception.getErrors() == ShelterException.ShelterErrors.SHELTER_NOT_UPDATED){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(exception.getErrors() == ShelterException.ShelterErrors.SHELTER_COULD_NOT_BE_REMOVED){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(exception.getErrors() == ShelterException.ShelterErrors.SURPASSED_CAPACITY_OF_THE_SHELTER){
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<String>(exception.getErrors().name(), status);
    }

}
