package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.CalorieHandler;
import com.softeng_grup6.vainsfitness.listeners.CalorieListener;
import com.softeng_grup6.vainsfitness.listeners.MealPlanHandler;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.CalorieAPI;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;
import java.util.Calendar;

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
    public static CalorieHandler calorieHandler = new CalorieHandler();
    public static MealPlanHandler mealPlanHandler = new MealPlanHandler();
    public static UserAcntHandler userAcntHandler = new UserAcntHandler();
    String meal_name = "";
    ArrayList<String> meal_items = new ArrayList<>();
    String display_text = "Meal name - ";
    int count = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeal);
        addFromMealPlan = (Button)findViewById(R.id.add_from_mealplan);
        addButton = (Button)findViewById(R.id.add);
        doneButton = (Button)findViewById(R.id.done);
        title = (TextView)findViewById(R.id.title);
        mealDisplay = (TextView)findViewById(R.id.meal_display);
        descriptionTitle = (TextView)findViewById(R.id.meal_desc_title);
        mealDisplayTitle = (TextView)findViewById(R.id.meal_display_title);
        mealDetails = (EditText) findViewById(R.id.meal_name);
        mealDescription = (EditText) findViewById(R.id.meal_meth);
        meal_description_layout = (LinearLayout)findViewById(R.id.add_desc);
        if(UserInterfaceManager.getLoggedInUserType().equals("admin")){
            adminConfiguration();
        }else if (UserInterfaceManager.getLoggedInUserType().equals("client")){
            clientConfiguration();
        }
    }
    private void adminConfiguration(){
        addFromMealPlan.setVisibility(View.GONE);
        title.setText("Add New Meal Plan");
        descriptionTitle.setText("Preparation Method");
        mealDescription.setHint("Enter the meals method of preparation");
        mealDisplayTitle.setText("Meal Plan Details\n============================");
        mealDisplay.setText(display_text);
        mealDetails.setHint("Enter Meal Plan Name then press add");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add_text = mealDetails.getText().toString();
                if(add_text.length() > 1){
                    if(count > 0){
                        meal_items.add(add_text);
                        display_text = display_text+"\t\t"+add_text+"\n";

                    }else{
                        meal_name = add_text;
                        display_text = display_text+add_text+"\nItems -\n";
                        count++;
                        mealDetails.setHint("Enter the Items with quantity eg.(2 egg)");
                    }
                }else{
                    Toast.makeText(AddMeal.this, "Please enter some text before you submit", Toast.LENGTH_SHORT).show();
                }
                mealDetails.setText("");
                mealDisplay.setText(display_text);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String method = mealDescription.getText().toString();
                if(method.length() > 4){
                    CalorieAPI calorieAPI = new CalorieAPI(getApplicationContext());
                    //Toast.makeText(AddMeal.this, "meal 1: "+meal_items.get(0), Toast.LENGTH_SHORT).show();
                    calorieAPI.getCalorie("addmealplan",meal_items);
                    calorieHandler.setOnCalorieFetchListener(new CalorieListener() {
                        @Override
                        public void success(int calorie_value) {
                            MealPlan mealPlan = new MealPlan(meal_name,meal_items,calorie_value,method);
//                            Toast.makeText(AddMeal.this, "Meal Plan Name "+mealPlan.getName(), Toast.LENGTH_SHORT).show();
                            AdminSystem.getAdminProfile().getAdminMealPlans().addMealPlanToList(mealPlan);
                            NetworkManager session  = new NetworkManager();
                            session.updateMealPlans("amp",AdminSystem.getAdminProfile().getAdminMealPlans());
                            mealPlanHandler.setOnUpdateMealPlanListener(new NetSessionListener() {
                                @Override
                                public void succees() {
                                    Toast.makeText(AddMeal.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                    returnHome();
                                }

                                @Override
                                public void unsuccessful() {
                                    Toast.makeText(AddMeal.this, "Network Problem", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                        @Override
                        public void unsuccessFul() {
                            count = 0;
                            meal_name = "";
                            mealDetails.setText("");
                            display_text = "Meal name - ";
                            meal_items.clear();
                            mealDescription.setText("");
                            mealDisplay.setText(display_text);
                            mealDetails.setHint("Enter Meal name/item name then press add");
                           // Toast.makeText(AddMeal.this, "Network Issue", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(AddMeal.this, "Enter the method of preparation or a link", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private  void clientConfiguration(){
        meal_description_layout.setVisibility(View.GONE);
        title.setText("Add Meal For Today");
        mealDisplay.setText(display_text);
        mealDetails.setHint("Enter Meal name/item name then press add");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add_text = mealDetails.getText().toString();
                if(add_text.length() > 1){
                    if(count > 0){
                        meal_items.add(add_text);
                        display_text = display_text+"\t\t"+add_text+"\n";

                    }else{
                        meal_name = add_text;
                        display_text = display_text+add_text+"\nItems -\n";
                        count++;
                        mealDetails.setHint("Enter the Items that make up meal with quantity eg.(2 egg)");
                    }
                }else{
                    Toast.makeText(AddMeal.this, "Please enter some text before you add", Toast.LENGTH_SHORT).show();
                }
                mealDetails.setText("");
                mealDisplay.setText(display_text);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(meal_items.size() > 0){
                    CalorieAPI calorieAPI = new CalorieAPI(getApplicationContext());
                    //Toast.makeText(AddMeal.this, "meal 1: "+meal_items.get(0), Toast.LENGTH_SHORT).show();
                    calorieAPI.getCalorie("addmealplan",meal_items);
                    calorieHandler.setOnCalorieFetchListener(new CalorieListener() {
                        @Override
                        public void success(final int calorie_value) {
                            Calendar calendar = Calendar.getInstance();
                            Date todayDate = new Date(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
                            Meal meal = new Meal(meal_name,meal_items,calorie_value);
//                            Toast.makeText(AddMeal.this, ""+meal.getName(), Toast.LENGTH_SHORT).show();
                            ClientSystem.getClientProfile().getUserConsumption().addTodaysMeal(todayDate,meal);
                            ConsumptionManager consumptionManager = ClientSystem.getClientProfile().getUserConsumption();
                            NetworkManager session  = new NetworkManager();
                            session.setNetContext(getApplicationContext());
                            session.clientConsumptionUpdate("am",consumptionManager);
                            userAcntHandler.setConsumptionUpdateListener(new NetSessionListener() {
                                @Override
                                public void succees() {
                                    if(calorie_value > (ClientSystem.getClientProfile().getWorkOutPlan().getEstimatedDailyCalorieConsumption()/3)){
                                        //warn user
                                        Toast.makeText(AddMeal.this, "Calories "+calorie_value+" : \nFood item exceeds the recommended number of calories required for today", Toast.LENGTH_LONG).show();
                                    }else{
                                        //alert user that they are ok
                                        Toast.makeText(AddMeal.this, "Calories "+calorie_value+" : \nFood item is within the recommended number of calories required for today", Toast.LENGTH_SHORT).show();
                                    }
                                    returnHome();
                                }

                                @Override
                                public void unsuccessful() {
                                    Toast.makeText(AddMeal.this, "Network Problem", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        @Override
                        public void unsuccessFul() {
                            count = 0;
                            meal_name = "";
                            mealDetails.setText("");
                            display_text = "Meal name - ";
                            meal_items.clear();
                            mealDescription.setText("");
                            mealDisplay.setText(display_text);
                            mealDetails.setHint("Enter Meal name/item name then press add");
                            // Toast.makeText(AddMeal.this, "Network Issue", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(AddMeal.this, "Enter the meal detail before saving", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addFromMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAcntHandler.setConsumptionUpdateListener(null);
                Intent go = new Intent(getApplicationContext(), AddMealPlan.class);
                startActivity(go);
                finish();
            }
        });


    }

    private void returnHome(){
        userAcntHandler.setConsumptionUpdateListener(null);
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        finish();
    }
}
