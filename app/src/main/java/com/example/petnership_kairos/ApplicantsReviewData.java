package com.example.petnership_kairos;

public class ApplicantsReviewData {
    private String applicationID, applicantID, applicantName, applicantPetID, applicantPetName, applicationStatus , applicantDateApplied;

    public ApplicantsReviewData(String applicationID, String applicantID, String applicantName, String applicantPetID, String applicantPetName, String applicationStatus, String applicantDateApplied) {
        this.applicationID = applicationID;
        this.applicantID = applicantID;
        this.applicantName = applicantName;
        this.applicantPetID = applicantPetID;
        this.applicantPetName = applicantPetName;
        this.applicationStatus = applicationStatus;
        this.applicantDateApplied = applicantDateApplied;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPetID() {
        return applicantPetID;
    }

    public void setApplicantPetID(String applicantPetID) {
        this.applicantPetID = applicantPetID;
    }

    public String getApplicantPetName() {
        return applicantPetName;
    }

    public void setApplicantPetName(String applicantPetName) {
        this.applicantPetName = applicantPetName;
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

