package com.softeng_grup6.vainsfitness.utils;

public class Client extends User{
    private double weight = 0;
    private double height =0;

    public Client(String frstname, String lastname, int age, Date creation_date, String username, String password, double weight, double height) {
        super(frstname, lastname, age, creation_date, username, password);
        this.weight = weight;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
