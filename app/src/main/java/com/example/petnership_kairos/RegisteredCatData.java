package com.example.petnership_kairos;

public class RegisteredCatData {
    String imageName, petName, petAge, petSex, petBreed;

    public RegisteredCatData(String imageName, String petName, String petAge, String petSex, String petBreed) {
        this.imageName = imageName;
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petBreed = petBreed;
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