package com.softeng_grup6.vainsfitness.utils;

import com.google.firebase.firestore.DocumentReference;

public class User {
    private String frstname = null;
    private String lastname = null;
    private int age = 0;
    private Date creation_date = null;
    private String username = null;
    private String password = null;
    private String email = null;
    private String gender = null;
    private DocumentReference id = null;

    public DocumentReference getId() {
        return id;
    }

    public void setId(DocumentReference id) {
        this.id = id;
    }

    public User(String frstname, String lastname, int age, Date creation_date, String username, String password, String email) {
        this.frstname = frstname;
        this.lastname = lastname;
        this.age = age;
        this.creation_date = creation_date;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrstname() {
        return frstname;
    }

    public void setFrstname(String frstname) {
        this.frstname = frstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreation_date() {
        return creation_date;
    }
}
