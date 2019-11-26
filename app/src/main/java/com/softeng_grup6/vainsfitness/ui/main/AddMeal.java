package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.R;

public class AddMeal extends AppCompatActivity {
    private Button addFromMealPlan = null;
    private Button addButton = null;
    private Button doneButton = null;
    private TextView title = null;
    private TextView mealDisplay = null;
    private TextView descriptionTitle = null;
    private TextView mealDisplayTitle = null;
    private EditText mealDetails = null;
    private EditText mealDescription = null;
    private LinearLayout meal_description_layout = null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeal);
        addFromMealPlan = (Button)findViewById(R.id.add_from_mealplan);
        addButton = (Button)findViewById(R.id.add_meal);
        doneButton = (Button)findViewById(R.id.done);
        title = (TextView)findViewById(R.id.title);
        mealDisplay = (TextView)findViewById(R.id.meal_display);
        descriptionTitle = (TextView)findViewById(R.id.meal_desc_title);
        mealDisplayTitle = (TextView)findViewById(R.id.meal_display_title);
        mealDetails = (EditText) findViewById(R.id.meal_name);
        mealDescription = (EditText) findViewById(R.id.meal_meth);
        meal_description_layout = (LinearLayout)findViewById(R.id.add_desc);


        addFromMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(), AddMealPlan.class);
                startActivity(go);
                finish();
            }
        });
    }
}
