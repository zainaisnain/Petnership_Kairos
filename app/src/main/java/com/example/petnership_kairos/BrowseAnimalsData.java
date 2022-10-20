package com.example.petnership_kairos;

public class BrowseAnimalsData {

    private String animalName;
    private String animalAge; 
    private String animalSex; 
    private String animalBreed; 
    private Integer animalImage;

    public BrowseAnimalsData(String animalName, String animalAge, String animalSex, String animalBreed, Integer animalImage) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalBreed = animalBreed;
        this.animalImage = animalImage;
    }

    public String getanimalName() {
        return animalName;
    }
    public String getanimalAge() {
        return animalAge;
    }
    public String getanimalSex() {
        return animalSex;
    }
    public String getanimalBreed() {
        return animalBreed;
    }

    public Integer getanimalImage() {
        return animalImage;
    }

    public void setanimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setanimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public void setanimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    public void setanimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public void setanimalImage(Integer animalImage) {
        this.animalImage = animalImage;
    }

}
