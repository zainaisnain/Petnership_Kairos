package com.example.petnership_kairos;

public class RegisteredPetData {
    String petImageName, petName, petAge, petSex, petBreed;

    public RegisteredPetData(String petImageName, String petName, String petAge, String petSex, String petBreed) {
        this.petImageName = petImageName;
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petBreed = petBreed;
    }

    public String getPetImageName() {
        return petImageName;
    }

    public void setPetImageName(String petImageName) {
        this.petImageName = petImageName;
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
