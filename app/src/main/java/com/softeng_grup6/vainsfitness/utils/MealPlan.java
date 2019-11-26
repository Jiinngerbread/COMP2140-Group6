package com.softeng_grup6.vainsfitness.utils;

import java.util.ArrayList;

public class MealPlan extends Meal {
    String Method ;

    public MealPlan(String name, ArrayList<String> food_items, int calorie, String method) {
        super(name, food_items, calorie);
        Method = method;
    }
    public MealPlan(){
        super();
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }
}
