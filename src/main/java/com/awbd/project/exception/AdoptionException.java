package com.awbd.project.exception;

public class AdoptionException extends RuntimeException{

    public enum AdoptionErrors {
        INVALID_ADOPTION_DATE,
        ANIMAL_IS_UNDER_TREATMENT_AND_CANT_BE_ADOPTED_AT_THE_MOMENT,
        ANIMAL_IS_ALREADY_ADOPTED,
        ADOPTION_COULD_NOT_BE_SAVED,
        ADOPTION_NOT_FOUND
    }

    private AdoptionErrors errors;

    public AdoptionException(AdoptionErrors errors){
        this.errors =errors;
    }

    public AdoptionErrors getErrors() {
        return errors;
    }

    public static AdoptionException invalidAdoptionDate(){
        return new AdoptionException(AdoptionErrors.INVALID_ADOPTION_DATE);
    }

    public static AdoptionException animalUnderTreatment(){
        return new AdoptionException(AdoptionErrors.ANIMAL_IS_UNDER_TREATMENT_AND_CANT_BE_ADOPTED_AT_THE_MOMENT);
    }
    public static AdoptionException animalAlreadyAdopted(){
        return new AdoptionException(AdoptionErrors.ANIMAL_IS_ALREADY_ADOPTED);
    }
    public static AdoptionException adoptionNotSaved(){
        return new AdoptionException(AdoptionErrors.ADOPTION_COULD_NOT_BE_SAVED);
    }
    public static AdoptionException adoptionNotFound(){
        return new AdoptionException(AdoptionErrors.ADOPTION_NOT_FOUND);
    }
}
