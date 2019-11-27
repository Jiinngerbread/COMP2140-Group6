package com.softeng_grup6.vainsfitness.utils;

public class WorkOutPlan {
    private double expected_weight = 0;
    private  int timeToTake = 0;

    public WorkOutPlan(){}

    public WorkOutPlan(double expected_weight, int timeToTake) {
        this.expected_weight = expected_weight;
        this.timeToTake = timeToTake;
    }

    public double getExpected_weight() {
        return expected_weight;
    }

    public void setExpected_weight(double expected_weight) {
        this.expected_weight = expected_weight;
    }

    public int getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(int timeToTake) {
        this.timeToTake = timeToTake;
    }

}
