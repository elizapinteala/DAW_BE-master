package com.awbd.project.exception;

public class PersonException extends RuntimeException {

    private PersonErrors error;

    private PersonException(PersonErrors error){
        this.error=error;
    }

    public PersonErrors getError() {
        return error;
    }

    public enum PersonErrors {
        PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS,
        PERSON_NOT_FOUND,
        PERSON_COULD_NOT_BE_REMOVED
    }

    public static PersonException personWithSameEmailAlreadyExists(){
        return new PersonException(PersonErrors.PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS);
    }

    public static PersonException personNotFound(){
        return new PersonException(PersonErrors.PERSON_NOT_FOUND);
    }

    public static PersonException personCouldNotBeRemoved(){
        return new PersonException(PersonErrors.PERSON_COULD_NOT_BE_REMOVED);
    }

}
