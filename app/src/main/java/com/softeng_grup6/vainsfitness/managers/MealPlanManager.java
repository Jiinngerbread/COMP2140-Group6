package com.softeng_grup6.vainsfitness.managers;

import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;

public class MealPlanManager {
    private ArrayList<MealPlan> mealPlans = new ArrayList<>();
    private int number_of_mealplans = 0;

    public MealPlanManager(ArrayList<MealPlan> mealPlans, int number_of_mealplans) {
        this.mealPlans = mealPlans;
        this.number_of_mealplans = number_of_mealplans;
    }

    public MealPlanManager() {}

    public ArrayList<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(ArrayList<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }

    public int getNumber_of_mealplans() {
        return number_of_mealplans;
    }

    public void setNumber_of_mealplans(int number_of_mealplans) {
        this.number_of_mealplans = number_of_mealplans;
    }
    public void addMealPlanToList(MealPlan mealPlan){
        mealPlans.add(mealPlan);
        number_of_mealplans++;
    }
}
