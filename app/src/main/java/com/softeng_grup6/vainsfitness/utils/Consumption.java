package com.softeng_grup6.vainsfitness.utils;

import java.util.ArrayList;

public class Consumption {
    private ArrayList<Meal> meal_list = new ArrayList<Meal>();
    private Double totalCalorie = null;
    private Date date = null;

    public Consumption(){}

    public Consumption(ArrayList<Meal> meal_list, Double totalCalorie, Date date) {
        this.meal_list = meal_list;
        this.totalCalorie = totalCalorie;
        this.date = date;
    }

    public ArrayList<Meal> getMeal_list() {
        return meal_list;
    }

    public void setMeal_list(ArrayList<Meal> meal_list) {
        this.meal_list = meal_list;
    }

    public Double getTotalCalorie() {
        return totalCalorie;
    }

    public void setTotalCalorie(Double totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addMeal(Meal meal){
        this.meal_list.add(meal);
        this.totalCalorie += meal.getCalorie();
    }

    public void removeMeal(Meal meal){
        if(this.meal_list.contains(meal)){
            int indx = this.meal_list.indexOf(meal);
            this.totalCalorie -= this.meal_list.get(indx).getCalorie();
            this.meal_list.remove(indx);
        }
    }
}
