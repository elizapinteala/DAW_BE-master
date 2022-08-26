package com.awbd.project.exception;

public class DonationException extends RuntimeException{

    public enum DonationErrors{
        THE_AMOUNT_DONATED_CANNOT_BE_LOWER_THAN_1,
        DONATION_NOT_FOUND,
        INVALID_DONATION_DATE,
        DONATION_CANNOT_BE_UPDATED
    }

    private DonationErrors donationErrors;

    public DonationException(DonationErrors donationErrors){
        this.donationErrors = donationErrors;
    }

    public DonationErrors getDonationErrors() {
        return donationErrors;
    }

    public static DonationException amountCantBeLowerThanOne(){
        return new DonationException(DonationErrors.THE_AMOUNT_DONATED_CANNOT_BE_LOWER_THAN_1);
    }

    public static DonationException donationNotFound(){
        return new DonationException(DonationErrors.DONATION_NOT_FOUND);
    }

    public static DonationException invalidDonationDate(){
        return new DonationException(DonationErrors.INVALID_DONATION_DATE);
    }
    public static DonationException donationCantBeUpdated(){
        return new DonationException(DonationErrors.DONATION_CANNOT_BE_UPDATED);
    }
}
