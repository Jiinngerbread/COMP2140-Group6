package com.softeng_grup6.vainsfitness.listeners;

import com.softeng_grup6.vainsfitness.managers.MealPlanManager;

public interface MealPlanListener {
    void succees(MealPlanManager mealPlanManager);
    void unsuccessful();
}
