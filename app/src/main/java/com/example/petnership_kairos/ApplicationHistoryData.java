package com.example.petnership_kairos;

public class ApplicationHistoryData {
    private String applicationID;
    private String shelterID;
    private String petID;
    private String PetName;
    private String applicationStatus;
    private String applicantDateApplied;
    private String breed;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String birthday;

    public ApplicationHistoryData(String applicationID, String shelterID, String petID, String petName, String breed, String birthday, String desc, String applicationStatus, String applicantDateApplied) {
        this.applicationID = applicationID;
        this.shelterID = shelterID;
        this.petID = petID;
        PetName = petName;
        this.breed = breed;
        this.birthday = birthday;
        this.desc = desc;
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
