package com.example.petnership_kairos;

public class MCDMCriteriaValue  {

    private MCDMCriteria criteria;
    private int intensityLevel;
    private double value;

    public MCDMCriteriaValue(MCDMCriteria criteria, int intensityLevel) {
        super();
        this.criteria = criteria;
        this.intensityLevel = intensityLevel;
    }

    public MCDMCriteriaValue() {
        super();
    }

    public MCDMCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(MCDMCriteria criteria) {
        this.criteria = criteria;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }
}
