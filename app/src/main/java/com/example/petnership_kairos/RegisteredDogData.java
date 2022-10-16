package com.example.petnership_kairos;

public class RegisteredDogData {
    String dogImageName, dogName, dogAge, dogSex, dogBreed;

    public RegisteredDogData(String dogImageName, String dogName, String dogAge, String dogSex, String dogBreed) {
        this.dogImageName = dogImageName;
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogSex = dogSex;
        this.dogBreed = dogBreed;
    }

    public String getDogImageName() {
        return dogImageName;
    }

    public void setDogImageName(String dogImageName) {
        this.dogImageName = dogImageName;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogSex() {
        return dogSex;
    }

    public void setDogSex(String dogSex) {
        this.dogSex = dogSex;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }
}
