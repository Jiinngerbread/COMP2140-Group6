package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMealPlan extends AppCompatActivity {
    private Button addMealPlan = null;
    private Button back = null;
    private ListView mealList = null;
    public  ArrayList<String> mealPlansPositions = new ArrayList<>();
    public static UserAcntHandler userAcntHandler= new UserAcntHandler();
    Date today;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmealplan);
        addMealPlan = (Button)findViewById(R.id.add);
        back = (Button)findViewById(R.id.back);
        mealList = (ListView)findViewById(R.id.mealList);

        Calendar c = Calendar.getInstance();
        today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));

        final MealPlanListAdapter mealPlanListAdapter = new MealPlanListAdapter(getApplicationContext(), ClientSystem.getClientProfile().getAdminMealPlans().getMealPlans());

        mealList.setAdapter(mealPlanListAdapter);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = view.findViewById(R.id.select);
                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                    mealPlansPositions.remove(""+position);

                }else{
                    checkBox.setChecked(true);
                    mealPlansPositions.add(""+position);

                }
            }
        });

        addMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddMealPlan.this, ""+mealPlansPositions.get(0), Toast.LENGTH_SHORT).show();
                ArrayList<MealPlan> mealPlans = ClientSystem.getClientProfile().getAdminMealPlans().getMealPlans();
                if(mealPlansPositions.size() > 0){
                    for(int a = 0; a < mealPlansPositions.size(); a++){
                        ClientSystem.getClientProfile().getUserConsumption()
                                .getTodaysConsumption(today).addMeal(mealPlans.get(Integer.parseInt(mealPlansPositions.get(a))));
                    }
                }
                NetworkManager session = new NetworkManager();
                session.clientConsumptionUpdate("amp",ClientSystem.getClientProfile().getUserConsumption());
                userAcntHandler.setConsumptionUpdateListener(new NetSessionListener() {
                    @Override
                    public void succees() {
                        Toast.makeText(AddMealPlan.this, "Added!", Toast.LENGTH_SHORT).show();
                        returnHome();
                    }

                    @Override
                    public void unsuccessful() {
                        Toast.makeText(AddMealPlan.this, "Network Problem", Toast.LENGTH_SHORT).show();
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
    public  void returnHome(){
        userAcntHandler.setConsumptionUpdateListener(null);
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnHome();
    }
}
