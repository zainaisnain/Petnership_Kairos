package com.example.petnership_kairos;

public class ForReviewApplicantsInfo {
    String dateApplied, timeApplied, adopterID, adopterIntentions,
            petID, petType, petName, petBreed, petAge, petDescription, shelterID, applicationStatus;


    public ForReviewApplicantsInfo(String dateApplied, String timeApplied, String adopterID, String adopterIntentions, String petID, String petType, String petName, String petBreed, String petAge, String petDescription, String shelterID, String applicationStatus) {
        this.dateApplied = dateApplied;
        this.timeApplied = timeApplied;
        this.adopterID = adopterID;
        this.adopterIntentions = adopterIntentions;
        this.petID = petID;
        this.petType = petType;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.shelterID = shelterID;
        this.applicationStatus = applicationStatus;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getTimeApplied() {
        return timeApplied;
    }

    public void setTimeApplied(String timeApplied) {
        this.timeApplied = timeApplied;
    }

    public String getAdopterID() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }

    public String getAdopterIntentions() {
        return adopterIntentions;
    }

    public void setAdopterIntentions(String adopterIntentions) {
        this.adopterIntentions = adopterIntentions;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
