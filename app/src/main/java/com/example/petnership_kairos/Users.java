package com.example.petnership_kairos;
public class Users {
    public String email, password, username, userType;

    public Users(){

    }

    public Users(String email, String password,String username, String userType){
        this.email = email;
        this.password = password;
        this.username = username;
        this.userType = userType;
    }
}