package com.example.petnership_kairos;

public class Pet {
    public String petName, petAgeNum, petSex, petStatus, petDesc, imageName, petID;
    public boolean petExact;


    public Pet(){

    }

    public Pet(String petName, String petAgeNum, boolean petExact, String petSex,  String petStatus, String petDesc, String imageName, String petID) {
        this.petName = petName;
        this.petAgeNum = petAgeNum;
        this.petSex = petSex;
        this.petExact = petExact;
        this.petStatus = petStatus;
        this.petDesc = petDesc;
        this.imageName = imageName;
        this.petID = petID;
    }
}
