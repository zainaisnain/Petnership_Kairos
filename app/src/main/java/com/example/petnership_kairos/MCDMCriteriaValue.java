package com.example.petnership_kairos;

public class MCDMCriteriaValue  {

    private MCDMCriteria criteria;
    private double value;

    public MCDMCriteriaValue(MCDMCriteria criteria, double value) {
        super();
        this.criteria = criteria;
        this.value = value;
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
}
