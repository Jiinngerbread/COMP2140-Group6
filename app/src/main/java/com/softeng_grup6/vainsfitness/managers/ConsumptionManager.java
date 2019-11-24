package com.softeng_grup6.vainsfitness.managers;

import com.softeng_grup6.vainsfitness.utils.Consumption;

import java.util.ArrayList;

public class ConsumptionManager {
    private ArrayList<Consumption> totalConsumption = new ArrayList<Consumption>();



    public void addConsumption(Consumption consumption){
        this.totalConsumption.add(consumption);
    }

    public  void removeConsumption(Consumption consumption){
        if(this.totalConsumption.contains(consumption)){
            int indx = this.totalConsumption.indexOf(consumption);
            this.totalConsumption.remove(consumption);
        }
    }
}
