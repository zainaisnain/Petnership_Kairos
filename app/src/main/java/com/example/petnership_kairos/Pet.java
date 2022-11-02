package com.example.petnership_kairos;

public class Pet {
    public String petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, imageName, petID;


    public Pet(){

    }

    public Pet(String petName, String petAgeNum, String petAgeDD, String petAge, String petSex, String petStatus, String petDesc, String imageName, String petID) {
        this.petName = petName;
        this.petAgeNum = petAgeNum;
        this.petAgeDD = petAgeDD;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petStatus = petStatus;
        this.petDesc = petDesc;
        this.imageName = imageName;
        this.petID = petID;
    }
}
