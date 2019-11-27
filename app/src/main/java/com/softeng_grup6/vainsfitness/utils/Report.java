package com.softeng_grup6.vainsfitness.utils;

public class Report {
    private double progrssPercentage;

    public Report(double progrssPercentage) {
        this.progrssPercentage = progrssPercentage;
    }

    public double getProgrssPercentage() {
        return progrssPercentage;
    }

    public void setProgrssPercentage(double progrssPercentage) {
        this.progrssPercentage = progrssPercentage;
    }

    public Report(){}
}
