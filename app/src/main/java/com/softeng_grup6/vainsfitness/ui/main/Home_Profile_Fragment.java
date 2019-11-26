package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;

import at.grabner.circleprogress.CircleProgressView;

public class Home_Profile_Fragment extends Fragment {
    private Button edit_prof =null;
    private  Button add_user = null;
    private Button addMealPlan = null;
    private TextView disp_calorie = null;
    private TextView disp_calorie_title= null;
    private TextView disp_num_meal = null;
    private TextView disp_num_meal_title = null;
    private TextView disp_num_days = null;
    private TextView disp_num_day_title = null;
    private TextView disp_age = null;
    private TextView disp_gender = null;
    private TextView disp_goal = null;
    private CircleProgressView progressView = null;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_profile_fragment, container, false);
        edit_prof = view.findViewById(R.id.edit_profile);
        add_user = view.findViewById(R.id.add_user);
        addMealPlan = view.findViewById(R.id.add_meal_plan);
        disp_calorie = view.findViewById(R.id.tot_calorie);
        disp_calorie_title = view.findViewById(R.id.tot_calorie_title);
        disp_num_meal = view.findViewById(R.id.number_meals);
        disp_num_meal_title = view.findViewById(R.id.number_meals_title);
        disp_num_days = view.findViewById(R.id.day);
        disp_num_day_title = view.findViewById(R.id.day_title);
        disp_age = view.findViewById(R.id.age);
        disp_gender = view.findViewById(R.id.gender);
        disp_goal = view.findViewById(R.id.goal);
        progressView = view.findViewById(R.id.progress_circular);


        edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getContext(),AddUser.class);
                startActivity(go);
            }
        });
        addMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getContext(), AddMealPlan.class);
                startActivity(go);
            }
        });
        return view;
    }
}
