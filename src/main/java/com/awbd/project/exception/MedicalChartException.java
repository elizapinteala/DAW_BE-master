package com.awbd.project.exception;

public class MedicalChartException extends RuntimeException{

    private MedicalErrors medicalErrors;



    public enum MedicalErrors{
        ALREADY_EXISTS_CHART_FOR_THIS_ANIMAL,
        MEDICAL_CHART_NOT_FOUND,
        INVALID_CHART_DATE
    }

    private MedicalErrors errors;

    private MedicalChartException(MedicalErrors medicalErrors) {
        this.medicalErrors=medicalErrors;
    }

    public MedicalErrors getMedicalErrors() {
        return medicalErrors;
    }

    public static MedicalChartException medicalChartAlreadyExists(){
        return new MedicalChartException(MedicalErrors.ALREADY_EXISTS_CHART_FOR_THIS_ANIMAL);
    }

    public static MedicalChartException medicalChartNotFound() {
        return new MedicalChartException(MedicalErrors.MEDICAL_CHART_NOT_FOUND);
    }

    public static MedicalChartException invalidChartDate() {
        return new MedicalChartException(MedicalErrors.INVALID_CHART_DATE);
    }
}
