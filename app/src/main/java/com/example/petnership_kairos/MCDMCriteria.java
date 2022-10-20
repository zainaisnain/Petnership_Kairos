package com.example.petnership_kairos;

public class MCDMCriteria {

    private String name;
    private double weight;
    private boolean negative;

    public MCDMCriteria() {
        super();
    }

    public MCDMCriteria(String name, double weight) {
        super();
        this.name = name;
        this.weight = weight;
    }

    public MCDMCriteria(String name, double weight, boolean negative) {
        super();
        this.name = name;
        this.weight = weight;
        this.negative = negative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
}
