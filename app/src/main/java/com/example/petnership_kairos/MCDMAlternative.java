package com.example.petnership_kairos;

import java.util.ArrayList;
import java.util.List;

public class MCDMAlternative {

    private String name;
    private String age;
    private String desc;
    private String sex;
    private String status;
    private String type;
    private String shelter;
    private String id;
    private String breed;

    private String imageName;
    private List<MCDMCriteriaValue> criteriaValues;
    private double calculatedPerformanceScore;


    public MCDMAlternative(String name, List<MCDMCriteriaValue> criteriaValues, String age, String desc, String sex, String status, String type, String shelter, String imageName, String id, String breed) {
        super();
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.sex = sex;
        this.status = status;
        this.type = type;
        this.shelter = shelter;
        this.imageName = imageName;
        this.id = id;
        this.breed = breed;
        this.criteriaValues = criteriaValues;
    }

    public MCDMAlternative(String name) {
        super();
        this.name = name;
    }

    public MCDMAlternative() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MCDMCriteriaValue> getCriteriaValues() {
        return criteriaValues;
    }

    public void setCriteriaValues(List<MCDMCriteriaValue> criteriaValues) {
        this.criteriaValues = criteriaValues;
    }

    public void addCriteriaValue(MCDMCriteriaValue criteriaValue) {
        if (criteriaValues == null) {
            criteriaValues = new ArrayList<MCDMCriteriaValue>();
        }
        this.criteriaValues.add(criteriaValue);
    }

    public void addCriteriaValue(MCDMCriteria criteria, int value) {
        if (criteriaValues == null) {
            criteriaValues = new ArrayList<MCDMCriteriaValue>();
        }
        this.criteriaValues.add(new MCDMCriteriaValue(criteria, value));
    }

    public double getCalculatedPerformanceScore() {
        return calculatedPerformanceScore;
    }

    protected void setCalculatedPerformanceScore(double calculatedPerformanceScore) {
        this.calculatedPerformanceScore = calculatedPerformanceScore;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
