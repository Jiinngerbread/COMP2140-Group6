package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntCheckListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

import java.util.ArrayList;
import java.util.Calendar;

public class AddUser extends AppCompatActivity {
    private EditText firstnameField = null;
    private EditText lastnameField = null;
    private EditText ageField = null;
    private EditText genderField = null;
    private EditText emailField = null;
    private EditText usernameField = null;
    private EditText passwordField = null;
    private EditText heightField = null;
    private EditText curweightField = null;
    private EditText furWeightField = null;
    private EditText timeField = null;
    private Button addButton = null;
    private Button back = null;
    public static UserAcntHandler userAcntHandler= new UserAcntHandler();

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
        heightField = (EditText)findViewById(R.id.height);
        curweightField = (EditText)findViewById(R.id.weight);
        furWeightField = (EditText)findViewById(R.id.future_weight);
        timeField = (EditText)findViewById(R.id.worktime);
        addButton = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NetworkManager session = new NetworkManager();
                session.setNetContext(getApplicationContext());
                final String firstname = firstnameField.getText().toString();
                final String lastname = lastnameField.getText().toString();
                final int age = Integer.parseInt(ageField.getText().toString());
                final String gender = genderField.getText().toString();
                final String email = emailField.getText().toString();
                final String username = usernameField.getText().toString();
                final String password = passwordField.getText().toString();
                final Double height = Double.valueOf(heightField.getText().toString());
                final Double current_weight = Double.valueOf(curweightField.getText().toString());
                final Double future_weight = Double.valueOf(furWeightField.getText().toString());
                final int workout_time = Integer.parseInt(timeField.getText().toString());

                if(firstname.length() > 2 && lastname.length() > 2){
                    if(age > 0 && gender.length() >= 4 && email.length() >3){
                        if(height > 0 && current_weight > 0 && future_weight >0 && workout_time > 0){
                            if(username.length() > 2 && password.length() >3){
                                session.checkClientUsername(username);
                                userAcntHandler.setAccountCheckListener(new UserAcntCheckListener() {
                                    @Override
                                    public void userAvailable() {
                                        Calendar c = Calendar.getInstance();
                                        Client client = new Client(firstname,lastname,age,new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)),username,password,email,gender,current_weight,height);
                                        Report report = new Report(current_weight);
                                        WorkOutPlan workOutPlan = new WorkOutPlan(future_weight,workout_time);
                                        workOutPlan.generateEstDailyCalorConsumption(current_weight);
                                        session.addNewClient(client,report,workOutPlan);
                                        userAcntHandler.setUserAccountAddListener(new NetSessionListener() {
                                            @Override
                                            public void succees() {
                                                Toast.makeText(AddUser.this, username+" was added", Toast.LENGTH_SHORT).show();
                                                returnHome();
                                            }
                                            @Override
                                            public void unsuccessful() {
                                                Toast.makeText(AddUser.this, "Network Problem", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    @Override
                                    public void userUnavailable() {
                                        Toast.makeText(AddUser.this, "Username Unavailable", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else{
                                Toast.makeText(AddUser.this, "Please enter a valid username or password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AddUser.this, "Please enter all fitness info", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddUser.this, "Please enter an age gender or email", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddUser.this, "Please enter first or last name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

    }
    public void returnHome(){
        userAcntHandler.setUserAccountAddListener(null);
        userAcntHandler.setAccountCheckListener(null);
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        finish();
    }
}
