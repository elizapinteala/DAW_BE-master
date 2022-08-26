package com.awbd.project.exception;

public class ShelterException extends RuntimeException{



    public enum ShelterErrors{
        SHELTER_WITH_THE_SAME_ADDRESS_ALREADY_EXISTS,
        SHELTER_NOT_FOUND,
        SHELTER_NOT_UPDATED,
        SHELTER_COULD_NOT_BE_REMOVED,
        SURPASSED_CAPACITY_OF_THE_SHELTER
    }

    private ShelterErrors errors;

    public ShelterException(ShelterErrors errors) {
        this.errors = errors;
    }

    public ShelterErrors getErrors() {
        return errors;
    }

    public static ShelterException shelterWithSameAddressAlreadyExists(){
        return new ShelterException(ShelterErrors.SHELTER_WITH_THE_SAME_ADDRESS_ALREADY_EXISTS);
    }

    public static ShelterException shelterNotFound(){
        return new ShelterException(ShelterErrors.SHELTER_NOT_FOUND);
    }

    public static ShelterException shelterNotUpdated(){
        return new ShelterException(ShelterErrors.SHELTER_NOT_FOUND);
    }

    public static ShelterException shelterCouldNotBeDeleted() {
        return new ShelterException(ShelterErrors.SHELTER_COULD_NOT_BE_REMOVED);
    }
    public static ShelterException exceededCapacity(){
        return new ShelterException(ShelterErrors.SURPASSED_CAPACITY_OF_THE_SHELTER);
    }

}
