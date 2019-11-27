package com.softeng_grup6.vainsfitness.managers;

import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;

import java.util.ArrayList;

public class ConsumptionManager {
    private ArrayList<Consumption> totalConsumption = new ArrayList<Consumption>();
    private int recordedConsumptions = 0;

    public ConsumptionManager(int recordedConsumptions) {
        this.recordedConsumptions = recordedConsumptions;
    }

    public ConsumptionManager(){}

    public void addConsumption(Consumption consumption){
        this.totalConsumption.add(consumption);
        this.recordedConsumptions++;
    }

    public  void removeConsumption(Consumption consumption){
        if(this.totalConsumption.contains(consumption)){
            int indx = this.totalConsumption.indexOf(consumption);
            this.totalConsumption.remove(consumption);
            recordedConsumptions--;
        }
    }

    public ArrayList<Consumption> getTotalConsumption() {
        return totalConsumption;
    }

    public int getRecordedConsumptions() {
        return recordedConsumptions;
    }

    public void addTodaysMeal(Date date, Meal meal){
        boolean consumption_exist = false;
        int indx = 0;
        for(int a = 0; a < this.totalConsumption.size(); a++){
            if(totalConsumption.get(a).getDate().getDate().equals(date.getDate())){
                consumption_exist = true;
                indx = a;
                break;
            }
        }
        if(consumption_exist){
            totalConsumption.get(indx).addMeal(meal);
        }else{
            Consumption consumption = new Consumption(0.0,date);
            consumption.addMeal(meal);
            recordedConsumptions++;
            totalConsumption.add(consumption);
        }
    }
    public Consumption getTodaysConsumption(Date date){
        boolean consumption_exist = false;
        int indx = 0;
        for(int a = 0; a < this.totalConsumption.size(); a++) {
            if (totalConsumption.get(a).getDate().getDate().equals(date.getDate())) {
                consumption_exist = true;
                indx = a;
            }
        }
        if(consumption_exist){
            return totalConsumption.get(indx);
        }
        return null;
    }
}
