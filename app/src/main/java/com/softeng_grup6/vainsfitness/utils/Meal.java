package com.softeng_grup6.vainsfitness.utils;

import java.util.ArrayList;

public class Meal {

    private String name = null;
    private ArrayList<String> food_items= new ArrayList<String>();
    private int Calorie = 0;

    public Meal(String name, ArrayList<String> food_items, int calorie) {
        this.name = name;
        this.food_items = food_items;
        Calorie = calorie;
    }
    public Meal(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getFood_items() {
        return food_items;
    }

    public void setFood_items(ArrayList<String> food_items) {
        this.food_items = food_items;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int calorie) {
        Calorie = calorie;
    }

    public void addFoodItem(String foodItem){
        this.food_items.add(foodItem);
    }

    public void removeFoodItem(String foodItem){
        if(this.food_items.contains(food_items)){
            int indx = this.food_items.indexOf(foodItem);
            this.food_items.remove(indx);
        }
    }
}
