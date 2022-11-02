package com.example.petnership_kairos;

public class Shelter {

    public String shelterID, bizName, owner, email, username,
            website, contact, street, city, province, country;

    public Shelter(){

    }

    public Shelter(String shelterID, String bizName, String owner, String email, String username, String website,
                   String contact, String street, String city, String province,
                   String country){

        this.shelterID = shelterID;
        this.bizName = bizName;
        this.owner = owner;
        this.email = email;
        this.username = username;
        this.website = website;
        this.contact = contact;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
    }

}
