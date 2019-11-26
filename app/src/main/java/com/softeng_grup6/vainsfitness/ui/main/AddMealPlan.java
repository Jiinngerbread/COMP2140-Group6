package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.R;

public class AddMealPlan extends AppCompatActivity {
    private Button addMealPlan = null;
    private Button back = null;
    private ListView mealList = null;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmealplan);
        addMealPlan = (Button)findViewById(R.id.add);
        back = (Button)findViewById(R.id.back);
        mealList = (ListView)findViewById(R.id.mealList);



    }
}
