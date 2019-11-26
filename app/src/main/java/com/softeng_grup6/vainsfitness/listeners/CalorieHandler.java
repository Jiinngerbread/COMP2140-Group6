package com.softeng_grup6.vainsfitness.listeners;

public class CalorieHandler {
    private CalorieListener calorieListener;

    public void setOnCalorieFetchListener(CalorieListener calorieFetchListener){
        this.calorieListener = calorieFetchListener;
    };

    public void fetchCalorieSuccess(int calorie_val){calorieListener.success(calorie_val);}
    public  void fetchCAlorieFail(){calorieListener.unsuccessFul();}
}
