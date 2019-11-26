package com.softeng_grup6.vainsfitness.listeners;

import com.softeng_grup6.vainsfitness.managers.MealPlanManager;

public class MealPlanHandler {
    private  NetSessionListener netSessionListener;
    private  MealPlanListener mealPlanListener;
     public void setOnUpdateMealPlanListener(NetSessionListener netSessionListener){
         this.netSessionListener = netSessionListener;
     }

     public void setOnLoadMealPlanManager(MealPlanListener mealPlanListener){
         this.mealPlanListener = mealPlanListener;
     }

     public void mealPlanUpdateSuccessfull(){netSessionListener.succees();}
     public void mealPlanUpdateUnsuccessful(){netSessionListener.unsuccessful();}
    public void mealPlanManagerLoadSuccessfull(MealPlanManager mealPlanManager){mealPlanListener.succees(mealPlanManager);}
    public void mealPlanManagerLoadUnsuccessful(){mealPlanListener.unsuccessful();}
}
