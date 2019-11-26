package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.R;

public class ConsumptionList extends Fragment {
    private Button addMeal = null;
    private ListView mealList = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consumptionlist, container, false);
        addMeal = view.findViewById(R.id.add_meal);
        mealList = view.findViewById(R.id.mealList);


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
