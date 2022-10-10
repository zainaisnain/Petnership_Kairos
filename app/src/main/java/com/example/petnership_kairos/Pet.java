package com.example.petnership_kairos;

public class Pet {
    public String petName, petAge, petSex, petDesc, petImage, petID;

    public Pet(){

    }

    public Pet(String petName, String petAge, String petSex, String petDesc, String petImage, String petID){
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petDesc = petDesc;
        this.petImage = petImage;
        this.petID = petID;
    }
}
