package com.example.petnership_kairos;

public class DogAnswers {
    public int q1,q2,q3,q4,q5,q6,q7, q8, q9, q11;
    public String shelter, petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, imageName, petID, q10, petType;

    public DogAnswers(){}
    public DogAnswers(String shelter, String petName, String petAgeNum, String petAgeDD,  String petAge, String petSex, String petStatus, String petDesc,
                      String imageName, String petID, int q1, int q2, int q3, int q4, int q5,
                      int q6, int q7, int q8, int q9, String q10, int q11, String petType){
        this.shelter=shelter;
        this.petName = petName;
        this.petAgeNum=petAgeNum;
        this.petAgeDD=petAgeDD;
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
        this.q10=q10;
        this.q11=q11;
        this.petType = petType;
    }
}
