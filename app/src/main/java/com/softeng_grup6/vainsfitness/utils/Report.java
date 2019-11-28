package com.softeng_grup6.vainsfitness.utils;

import com.softeng_grup6.vainsfitness.systems.ClientSystem;

import java.util.ArrayList;
import java.util.Calendar;

public class Report {
    private double progrssPercentage;
    private double weightLoss;
    private int totalDays;
    private double avgCalorieConsumption;
    private double avgDailyWeightLoss;
    private int numWeightChange;
    private double startWeight;
    private double status = 0;

    public Report(){}

    public Report(double startWeight){
        this.startWeight = startWeight;
    }

    public Report(double progrssPercentage, double weightLoss, int totalDays, double avgCalorieConsumption, double avgDailyWeightLoss) {
        this.progrssPercentage = progrssPercentage;
        this.weightLoss = weightLoss;
        this.totalDays = totalDays;
        this.avgCalorieConsumption = avgCalorieConsumption;
        this.avgDailyWeightLoss = avgDailyWeightLoss;
    }


    public double getProgrssPercentage() {
        return progrssPercentage;
    }


    public void setProgrssPercentage(double progrssPercentage) {
        this.progrssPercentage = progrssPercentage;
    }

    public double getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(double weightLoss) {
        this.weightLoss = weightLoss;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public double getAvgCalorieConsumption() {
        return avgCalorieConsumption;
    }

    public void setAvgCalorieConsumption(double avgCalorieConsumption) {
        this.avgCalorieConsumption = avgCalorieConsumption;
    }

    public double getAvgDailyWeightLoss() {
        return avgDailyWeightLoss;
    }

    public void setAvgDailyWeightLoss(double avgDailyWeightLoss) {
        this.avgDailyWeightLoss = avgDailyWeightLoss;
    }

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public int getNumWeightChange() {
        return numWeightChange;
    }

    public void setNumWeightChange(int numWeightChange) {
        this.numWeightChange = numWeightChange;
    }

    public void generateDaysSinceCreation(){
        Calendar c = Calendar.getInstance();
        Date today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        this.totalDays = today.getDay() - ClientSystem.getClientProfile().getClientDetail().getCreation_date().getDay();
    }

    public void generateProgressPercentage(){
        this.progrssPercentage = ((ClientSystem.getClientProfile().getWorkOutPlan().getExpected_weight()
                /ClientSystem.getClientProfile().getClientDetail().getWeight())
                /**(this.totalDays/ClientSystem.getClientProfile().getWorkOutPlan().getTimeToTake())*/)*100;
    }

    public void generateWeightLoss(){
        this.weightLoss = this.startWeight - ClientSystem.getClientProfile().getClientDetail().getWeight();
    }

    public void generateAverageWeightLoss(double newWeight){
        this.avgDailyWeightLoss = ((this.avgDailyWeightLoss*this.numWeightChange)+(ClientSystem.getClientProfile().getClientDetail().getWeight()- newWeight))/(this.numWeightChange+1);
        this.numWeightChange++;
    }


    public void generateAverageCalorieConsumption(){
        ArrayList<Consumption> consumptionlist = ClientSystem.getClientProfile().getUserConsumption().getTotalConsumption();
        double totalCalor = 0;
        for(int a = 0; a < consumptionlist.size(); a++){
            totalCalor += consumptionlist.get(a).getTotalCalorie();
        }
        this.avgCalorieConsumption = totalCalor/consumptionlist.size();
    }

    public void generateStatus(){
        double estCalorconsump = ClientSystem.getClientProfile().getWorkOutPlan().getEstimatedDailyCalorieConsumption();
        double statusNumber = (this.avgCalorieConsumption/estCalorconsump);
        this.status = statusNumber;

    }

    public void generateReport(double newWeight){
        this.generateAverageWeightLoss(newWeight);
        this.generateDaysSinceCreation();
        this.generateAverageCalorieConsumption();
        this.generateWeightLoss();
        this.generateProgressPercentage();
        this.generateStatus();
    }

    public void generateReport(){
        this.generateDaysSinceCreation();
        this.generateAverageCalorieConsumption();
        this.generateWeightLoss();
        this.generateProgressPercentage();
        this.generateStatus();
    }
}
