package com.example.petnership_kairos;

public class CatAnswers {
    public int q1,q2,q3,q4,q5,q6,q7, q8;
    public String shelter, petName, petAge, petSex, petStatus, petDesc, imageName, petID, q9, petType;

    public CatAnswers(){}

    public CatAnswers(String shelter, String petName, String petAge, String petSex, String petStatus, String petDesc, String imageName, String petID,
                      int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, String q9, String petType){
        this.shelter=shelter;
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petStatus = petStatus;
        this.petDesc = petDesc;
        this.imageName = imageName;
        this.petID = petID;
        this.q1=q1;
        this.q2=q2;
        this.q3=q3;
        this.q4=q4;
        this.q5=q5;
        this.q6=q6;
        this.q7=q7;
        this.q8=q8;
        this.q9=q9;
        this.petType = petType;
    }
}
