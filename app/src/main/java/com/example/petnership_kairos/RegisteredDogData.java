package com.example.petnership_kairos;

public class RegisteredDogData {
    String petID, imageName, petName, petAge, petSex, petBreed;

    public RegisteredDogData(String petID, String imageName, String petName, String petAge, String petSex, String petBreed) {
        this.petID = petID;
        this.imageName = imageName;
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petBreed = petBreed;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }
}
