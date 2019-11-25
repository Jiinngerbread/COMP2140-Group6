package com.softeng_grup6.vainsfitness.utils;

import java.util.ArrayList;

public class Admin extends User{
    private int numberOfClient = 0;
    private ArrayList<String> clientsUsernames = new ArrayList<String>();

    public Admin(String frstname, String lastname, int age, Date creation_date, String username, String password,String email, int numberOfClient, ArrayList<String> clientsUsernames) {
        super(frstname, lastname, age, creation_date, username, password,email);
        this.numberOfClient = numberOfClient;
        this.clientsUsernames = clientsUsernames;
    }

    public int getNumberOfClient() {
        return numberOfClient;
    }

    public ArrayList<String> getClientsUsernames() {
        return clientsUsernames;
    }

    public void addClient(String username){
        this.clientsUsernames.add(username);
        this.numberOfClient++;
    }

    public void removeCLient(String username){
        if(this.clientsUsernames.contains(username)){
            int indx = this.clientsUsernames.indexOf(username);
            this.clientsUsernames.remove(indx);
            this.numberOfClient --;
        }
    }
}
