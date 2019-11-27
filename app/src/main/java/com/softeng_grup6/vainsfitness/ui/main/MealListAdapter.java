package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter<Meal> {

    public MealListAdapter(Context context, ArrayList<Meal> meals) {
        super(context, 0,meals);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Meal meal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView items = (TextView) convertView.findViewById(R.id.items);
        TextView method = (TextView) convertView.findViewById(R.id.method);
        TextView calorie = (TextView) convertView.findViewById(R.id.calorie);
        String meal_items = "";
        name.setText(meal.getName());
        calorie.setText("calorie: "+meal.getCalorie());
        for(int a = 0; a <meal.getFood_items().size(); a++ ){
            meal_items = meal_items+meal.getFood_items().get(a)+"\n";
        }
        items.setText(meal_items);
        if(meal instanceof MealPlan){
            method.setVisibility(View.VISIBLE);
            method.setText(((MealPlan) meal).getMethod());
        }else{
            method.setVisibility(View.GONE);
        }
        return convertView;
    }
}
