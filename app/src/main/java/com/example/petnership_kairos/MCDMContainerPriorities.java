package com.example.petnership_kairos;

import java.util.ArrayList;
import java.util.List;

public class MCDMContainerPriorities {
    public double high;
    public double medium;
    public double low;

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public MCDMContainerPriorities() {

    }
    public MCDMContainerPriorities(double high, double medium, double low) {
        this.high = high;
        this.medium = medium;
        this.low = low;

    }


}
