package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;
import java.util.Calendar;

public class ConsumptionList extends Fragment {
    private Button addMeal = null;
    private ListView mealList = null;
    private MealListAdapter mealListAdapter;
    private  MealPlansAdapter mealListAdapter2;
    public static int mealPosition = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consumptionlist, container, false);
        addMeal = view.findViewById(R.id.add_meal);
        mealList = view.findViewById(R.id.mealList);
        if(UserInterfaceManager.getLoggedInUserType().equals("admin")){
            adminConfig();
        }else if (UserInterfaceManager.getLoggedInUserType().equals("client")){
            clientConfig();
        }


        return view;
    }

    private void clientConfig(){
        Calendar c = Calendar.getInstance();
        Date today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        Consumption consumption = ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today);
        if(consumption != null){
            mealListAdapter = new MealListAdapter(getContext(), consumption.getMeal_list());
        }else{
            mealListAdapter = new MealListAdapter(getContext(), new ArrayList<Meal>());
        }

        mealList.setAdapter(mealListAdapter);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConsumptionList.mealPosition = position;
                Intent go = new Intent(getContext(), RemoveMeal.class);
                startActivity(go);
            }
        });


        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go  = new Intent(getContext(), AddMeal.class);
                startActivity(go);

            }
        });
    }
    private void adminConfig(){
        ArrayList<MealPlan> meals = AdminSystem.getAdminProfile().getAdminMealPlans().getMealPlans();
        mealListAdapter2 = new MealPlansAdapter(getContext(), meals);

        mealList.setAdapter(mealListAdapter2);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConsumptionList.mealPosition = position;
                Intent go = new Intent(getContext(), RemoveMeal.class);
                startActivity(go);
            }
        });

        addMeal.setText("Add Meal Plan");
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getContext(), AddMeal.class);
                startActivity(go);
            }
        });

    }
}
