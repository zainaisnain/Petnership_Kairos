package com.example.petnership_kairos;

import java.util.ArrayList;
import java.util.List;

public class MCDMAlternative {

    private String name;
    private List<MCDMCriteriaValue> criteriaValues;
    private double calculatedPerformanceScore;

    public MCDMAlternative(String name, List<MCDMCriteriaValue> criteriaValues) {
        super();
        this.name = name;
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

    public void addCriteriaValue(MCDMCriteria criteria, double value) {
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
}
