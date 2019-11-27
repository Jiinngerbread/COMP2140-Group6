package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.utils.Meal;
import com.softeng_grup6.vainsfitness.utils.MealPlan;

import java.util.ArrayList;

public class MealPlansAdapter extends ArrayAdapter<MealPlan>{

    public MealPlansAdapter(Context context, ArrayList<MealPlan> meals) {
        super(context, 0,meals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MealPlan meal = getItem(position);
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
        items.setText("Ingredients:\n"+meal_items);
        method.setText("Method:\n"+meal.getMethod());
        return convertView;
    }
}
