package com.example.petnership_kairos;

public class ApplicantsReviewData {
    private String applicantName;
    private String applicantPet;
    private Integer applicantImage;

    public ApplicantsReviewData(String applicantName, String applicantPet, Integer applicantImage) {
        this.applicantName = applicantName;
        this.applicantPet = applicantPet;
        this.applicantImage = applicantImage;
    }

    public String getapplicantName() {
        return applicantName;
    }
    public String getapplicantPet() {
        return applicantPet;
    }
    public Integer getapplicantImage() {
        return applicantImage;
    }

    public void setapplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setapplicantPet(String applicantPet) {
        this.applicantPet = applicantPet;
    }

    public void setapplicantImage(Integer applicantImage) {
        this.applicantImage = applicantImage;
    }
}
