package com.awbd.project.exception;

public class AnimalException extends RuntimeException{

    public enum AnimalErrors {
        ANIMAL_NOT_FOUND
    }

    private AnimalErrors errors;

    public AnimalException(AnimalErrors errors) {
        this.errors = errors;
    }

    public AnimalErrors getErrors() {
        return errors;
    }

    public static AnimalException animalNotFound(){
        return new AnimalException(AnimalErrors.ANIMAL_NOT_FOUND);
    }
}
