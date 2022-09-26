package com.example.petnership_kairos;

public class Adopter {
    public String fname, lname, email, username, password,
            contact, street, city, province, country, gender, birthday;

    public Adopter(){

    }

    public Adopter(String fname, String lname, String email, String username, String password,
                   String contact, String street, String city, String province, String country,
                   String gender, String birthday){

        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
    }
}
