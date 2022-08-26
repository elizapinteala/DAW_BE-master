package com.awbd.project.exception;

public class FacilityException  extends RuntimeException{

    public enum FacilityErrors{
        FACILITY_WITH_SAME_NAME_ALREADY_EXISTS,
        FACILITY_DOES_NOT_EXIST
    }

    private FacilityErrors facilityErrors;

    public FacilityException(FacilityErrors facilityErrors) {
        this.facilityErrors = facilityErrors;
    }

    public FacilityErrors getFacilityErrors() {
        return facilityErrors;
    }

    public static FacilityException facilityWithSameNameAlreadyExists(){
        return new FacilityException(FacilityErrors.FACILITY_WITH_SAME_NAME_ALREADY_EXISTS);
    }

    public static FacilityException facilityDoesNotExist(){
        return new FacilityException(FacilityErrors.FACILITY_DOES_NOT_EXIST);
    }
}
