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
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;

import java.util.ArrayList;
import java.util.Calendar;

public class ConsumptionList extends Fragment {
    private Button addMeal = null;
    private ListView mealList = null;
    private MealListAdapter mealListAdapter;
    public static int maealPosition = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consumptionlist, container, false);
        addMeal = view.findViewById(R.id.add_meal);
        mealList = view.findViewById(R.id.mealList);

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
                ConsumptionList.maealPosition = position;
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

        return view;
    }
}
