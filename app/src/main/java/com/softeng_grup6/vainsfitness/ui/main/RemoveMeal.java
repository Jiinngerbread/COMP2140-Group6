package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.MealPlanHandler;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.Calendar;

public class RemoveMeal extends AppCompatActivity {
    private TextView eventTitle = null;
    private Button removeButton = null;
    private Button back = null;
    public static UserAcntHandler userAcntHandler = new UserAcntHandler();
    public static MealPlanHandler mealPlanHandler= new MealPlanHandler();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removemeal);
        eventTitle = (TextView)findViewById(R.id.eventTitle);
        removeButton = (Button)findViewById(R.id.remove);
        back = (Button)findViewById(R.id.back);
        if(UserInterfaceManager.getLoggedInUserType() == "admin"){
            adminConfig();
        }else if(UserInterfaceManager.getLoggedInUserType() == "client"){
            clientCOnfig();
        }

    }
    public void adminConfig(){
        final MealPlan meal = AdminSystem.getAdminProfile().getAdminMealPlans().getMealPlans().get(ConsumptionList.mealPosition);
        eventTitle.setText("Remove "+meal.getName()+" Meal");

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSystem.getAdminProfile().getAdminMealPlans().removeMealPlanFromList(meal);
                NetworkManager session  = new NetworkManager();
                session.updateMealPlans("rmp",AdminSystem.getAdminProfile().getAdminMealPlans());
                mealPlanHandler.setOnUpdateMealPlanListener(new NetSessionListener() {
                    @Override
                    public void succees() {
                        Toast.makeText(RemoveMeal.this, "Removed Successfully", Toast.LENGTH_SHORT).show();
                        returnHome();
                    }

                    @Override
                    public void unsuccessful() {
                        Toast.makeText(RemoveMeal.this, "Network Problem", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });


    }

    public void clientCOnfig(){
        Calendar c = Calendar.getInstance();
        final Date today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        Consumption consumption = ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today);
        if(consumption != null){
            Meal meal =consumption.getMeal_list().get(ConsumptionList.mealPosition);
            eventTitle.setText("Remove "+meal.getName()+" Meal");
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today)
                            .removeMeal(ConsumptionList.mealPosition);
                    NetworkManager session = new NetworkManager();
                    session.clientConsumptionUpdate("rm",ClientSystem.getClientProfile().getUserConsumption());
                    userAcntHandler.setConsumptionUpdateListener(new NetSessionListener() {
                        @Override
                        public void succees() {
                            Toast.makeText(RemoveMeal.this, "Deleted!", Toast.LENGTH_SHORT).show();
                            returnHome();
                        }

                        @Override
                        public void unsuccessful() {
                            Toast.makeText(RemoveMeal.this, "Network Problem!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }

    public void returnHome(){
        mealPlanHandler.setOnUpdateMealPlanListener(null);
        userAcntHandler.setConsumptionUpdateListener(null);
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        finish();
    }
}
