package com.example.petnership_kairos;
public class Adopter {
    public String adopterID, fname, lname, email,
            contact, street, city, province, country, gender, birthday, imageName;

    boolean answeredCatQuestionnaire, answeredDogQuestionnaire;

    public Adopter(String adopterID, String fname, String lname, String email, String contact, String street, String city, String province, String country, String gender,
                   String birthday, String imageName, boolean answeredCatQuestionnaire, boolean answeredDogQuestionnaire) {
        this.adopterID = adopterID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contact = contact;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
        this.imageName = imageName;
        this.answeredCatQuestionnaire = answeredCatQuestionnaire;
        this.answeredDogQuestionnaire = answeredDogQuestionnaire;
    }

    public String getAdopterID() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isAnsweredCatQuestionnaire() {
        return answeredCatQuestionnaire;
    }

    public void setAnsweredCatQuestionnaire(boolean answeredCatQuestionnaire) {
        this.answeredCatQuestionnaire = answeredCatQuestionnaire;
    }

    public boolean isAnsweredDogQuestionnaire() {
        return answeredDogQuestionnaire;
    }

    public void setAnsweredDogQuestionnaire(boolean answeredDogQuestionnaire) {
        this.answeredDogQuestionnaire = answeredDogQuestionnaire;
    }
}

