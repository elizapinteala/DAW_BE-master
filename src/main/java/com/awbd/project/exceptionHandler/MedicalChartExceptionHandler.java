package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.MedicalChartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MedicalChartExceptionHandler {

    @ExceptionHandler(MedicalChartException.class)
    public final ResponseEntity<String> handleMedicalChartException(MedicalChartException e, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(e.getMedicalErrors() == MedicalChartException.MedicalErrors.ALREADY_EXISTS_CHART_FOR_THIS_ANIMAL){
            status = HttpStatus.CONFLICT;
        }
        else if(e.getMedicalErrors() == MedicalChartException.MedicalErrors.MEDICAL_CHART_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        else if(e.getMedicalErrors() == MedicalChartException.MedicalErrors.INVALID_CHART_DATE){
            status = HttpStatus.FORBIDDEN;
        }
       return new ResponseEntity<String>(e.getMedicalErrors().name(), status);
    }

}
