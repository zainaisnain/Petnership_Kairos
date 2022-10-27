package com.example.petnership_kairos;

public class ForReviewApplicantsInfo {
    String adopterID, adopterEmail, petID, timeStamp;

    public ForReviewApplicantsInfo(String adopterID, String adopterEmail, String petID, String timeStamp) {
        this.adopterID = adopterID;
        this.adopterEmail = adopterEmail;
        this.petID = petID;
        this.timeStamp = timeStamp;
    }

    public String getAdopterID() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }

    public String getAdopterEmail() {
        return adopterEmail;
    }

    public void setAdopterEmail(String adopterEmail) {
        this.adopterEmail = adopterEmail;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
