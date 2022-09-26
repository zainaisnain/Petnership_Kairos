package com.example.petnership_kairos;

public class Shelter {

    public String bizName, owner, email, username, password,
            website, contact, street, city, province, country, tin, imageName;

    public Shelter(){

    }

    public Shelter(String bizName, String owner, String email, String username, String password,
                   String website, String contact, String street, String city, String province,
                   String country, String tin, String imageName){

        this.bizName = bizName;
        this.owner = owner;
        this.email = email;
        this.username = username;
        this.password = password;
        this.website = website;
        this.contact = contact;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.tin = tin;
        this.imageName = imageName;
    }

}
