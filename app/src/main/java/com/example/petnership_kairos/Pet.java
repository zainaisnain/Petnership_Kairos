package com.example.petnership_kairos;

public class Pet {
    public String petName, petAge, petSex, petStatus, petDesc, imageName, petID;

    public Pet(){

    }


    public Pet(String petName, String petAge, String petSex, String petStatus, String petDesc, String imageName, String petID){
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petStatus = petStatus;
        this.petDesc = petDesc;
        this.imageName = imageName;
        this.petID = petID;
    }
}
