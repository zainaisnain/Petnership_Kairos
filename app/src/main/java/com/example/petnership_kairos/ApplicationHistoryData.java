package com.example.petnership_kairos;

public class ApplicationHistoryData {
    private String applicationID, shelterID, petID, PetName,
            applicationStatus , applicantDateApplied;

    public ApplicationHistoryData(String applicationID, String shelterID, String petID, String petName, String applicationStatus, String applicantDateApplied) {
        this.applicationID = applicationID;
        this.shelterID = shelterID;
        this.petID = petID;
        PetName = petName;
        this.applicationStatus = applicationStatus;
        this.applicantDateApplied = applicantDateApplied;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicantDateApplied() {
        return applicantDateApplied;
    }

    public void setApplicantDateApplied(String applicantDateApplied) {
        this.applicantDateApplied = applicantDateApplied;
    }
}
