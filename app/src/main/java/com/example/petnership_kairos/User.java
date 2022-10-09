package com.example.petnership_kairos;

public class User {
    public String email, password, username, userType;

    public User(){

    }

    public User(String email, String password,String username, String userType){
        this.email = email;
        this.password = password;
        this.username = username;
        this.userType = userType;
    }
}
