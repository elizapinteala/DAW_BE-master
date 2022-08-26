package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.FacilityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FacilityExceptionHandler {

    @ExceptionHandler(FacilityException.class)
    public final ResponseEntity<String> handleFacilityException(FacilityException facilityException, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(facilityException.getFacilityErrors() == FacilityException.FacilityErrors.FACILITY_WITH_SAME_NAME_ALREADY_EXISTS){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(facilityException.getFacilityErrors() == FacilityException.FacilityErrors.FACILITY_DOES_NOT_EXIST){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<String>(facilityException.getFacilityErrors().name(), status);
    }
}
