package com.example.petnership_kairos;
public class User {
    public String userID, email, userType;
    public boolean manuallyVerified;

    public User(){

    }

    public User(String userID, String email, String userType, boolean manuallyVerified) {
        this.userID = userID;
        this.email = email;
        this.userType = userType;
        this.manuallyVerified = manuallyVerified;
    }
}