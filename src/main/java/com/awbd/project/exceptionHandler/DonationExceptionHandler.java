package com.awbd.project.exceptionHandler;

import com.awbd.project.exception.DonationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DonationExceptionHandler {

    @ExceptionHandler(DonationException.class)
    public final ResponseEntity<String> handleDonationException(DonationException donationException, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(donationException.getDonationErrors() == DonationException.DonationErrors.THE_AMOUNT_DONATED_CANNOT_BE_LOWER_THAN_1){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(donationException.getDonationErrors() == DonationException.DonationErrors.DONATION_NOT_FOUND){
            status = HttpStatus.NOT_FOUND;
        }
        else if(donationException.getDonationErrors() == DonationException.DonationErrors.INVALID_DONATION_DATE){
            status = HttpStatus.BAD_REQUEST;
        }
        else if(donationException.getDonationErrors() == DonationException.DonationErrors.DONATION_CANNOT_BE_UPDATED){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<String>(donationException.getDonationErrors().name(),status);
    }

}
