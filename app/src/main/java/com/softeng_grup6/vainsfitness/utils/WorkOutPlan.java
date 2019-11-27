package com.softeng_grup6.vainsfitness.utils;

import com.softeng_grup6.vainsfitness.systems.ClientSystem;

public class WorkOutPlan {
    private double expected_weight = 0;
    private  int timeToTake = 0;
    private double estimatedDailyCalorieConsumption = 0;

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

    public double getEstimatedDailyCalorieConsumption() {
        return estimatedDailyCalorieConsumption;
    }

    public void setEstimatedDailyCalorieConsumption(double estimatedDailyCalorieConsumption) {
        this.estimatedDailyCalorieConsumption = estimatedDailyCalorieConsumption;
    }

    public int getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(int timeToTake) {
        this.timeToTake = timeToTake;
    }

    public void generateEstDailyCalorConsumption(){
        double weight_loss = ClientSystem.getClientProfile().getClientDetail().getWeight() - expected_weight;
        double calorieloss = weight_loss * 3500;
        this.estimatedDailyCalorieConsumption = (calorieloss/(weight_loss+timeToTake))*1.5;
    }
    public void generateEstDailyCalorConsumption(double currentWeight){
        double weight_loss =  currentWeight - expected_weight;
        double calorieloss = weight_loss * 3500;
        this.estimatedDailyCalorieConsumption = (calorieloss/(weight_loss+timeToTake))*1.5;
    }

}
