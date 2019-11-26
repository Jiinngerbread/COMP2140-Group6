package com.softeng_grup6.vainsfitness.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.R;

public class AddUser extends AppCompatActivity {
    private EditText firstnameField = null;
    private EditText lastnameField = null;
    private EditText ageField = null;
    private EditText genderField = null;
    private EditText emailField = null;
    private EditText usernameField = null;
    private EditText passwordField = null;
    private Button addButton = null;
    private Button back = null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);
        firstnameField = (EditText)findViewById(R.id.fname);
        lastnameField = (EditText)findViewById(R.id.lname);
        ageField = (EditText)findViewById(R.id.age);
        genderField = (EditText)findViewById(R.id.gender);
        emailField = (EditText)findViewById(R.id.email);
        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.pswd);
        addButton = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);

    }
}
