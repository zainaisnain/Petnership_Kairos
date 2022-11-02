package com.example.petnership_kairos;
public class User {
    public String email, username, userType;
    public boolean manuallyVerified;

    public User(){

    }

    public User(String email, String username, String userType, boolean manuallyVerified) {
        this.email = email;
        this.username = username;
        this.userType = userType;
        this.manuallyVerified = manuallyVerified;
    }
}