package com.example.petnership_kairos;
public class Adopter {
    public String fname, lname, email, username,
            contact, street, city, province, country, gender, birthday, imageName;

    public Adopter(){

    }

    public Adopter(String fname, String lname, String email, String username,
                   String contact, String street, String city, String province, String country,
                   String gender, String birthday, String imageName){

        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.contact = contact;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
        this.imageName = imageName;
    }
}

