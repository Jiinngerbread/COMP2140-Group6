package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Profile;
import com.softeng_grup6.vainsfitness.utils.User;

import java.util.Calendar;

import at.grabner.circleprogress.CircleProgressView;

public class Home_Profile_Fragment extends Fragment {
    private Button edit_prof =null;
    private  Button add_user = null;
    private Button addMealPlan = null;
    private TextView disp_name = null;
    private TextView disp_calorie = null;
    private TextView disp_calorie_title= null;
    private TextView disp_num_meal = null;
    private TextView disp_num_meal_title = null;
    private TextView disp_num_days = null;
    private TextView disp_num_day_title = null;
    private TextView disp_age = null;
    private TextView disp_gender = null;
    private TextView disp_goal = null;
    private TextView disp_title = null;
    private CircleProgressView progressView = null;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_profile_fragment, container, false);
        edit_prof = view.findViewById(R.id.edit_profile);
        add_user = view.findViewById(R.id.add_user);
        addMealPlan = view.findViewById(R.id.add_meal_plan);
        disp_name = view.findViewById(R.id.name);
        disp_calorie = view.findViewById(R.id.tot_calorie);
        disp_calorie_title = view.findViewById(R.id.tot_calorie_title);
        disp_num_meal = view.findViewById(R.id.number_meals);
        disp_num_meal_title = view.findViewById(R.id.number_meals_title);
        disp_num_days = view.findViewById(R.id.day);
        disp_num_day_title = view.findViewById(R.id.day_title);
        disp_age = view.findViewById(R.id.age);
        disp_gender = view.findViewById(R.id.gender);
        disp_goal = view.findViewById(R.id.goal);
        disp_title = view.findViewById(R.id.title);
        progressView = view.findViewById(R.id.progress_circular);


        if(UserInterfaceManager.getLoggedInUserType().equals("admin")){
            adminConfiguration();
        }else if(UserInterfaceManager.getLoggedInUserType().equals("client")){
            clientConfiguration();
        }
        return view;
    }

    private void adminConfiguration(){
        Admin adminProfileUser = AdminSystem.getAdminProfile().getAdminDetail();
        Profile adminProfile = AdminSystem.getAdminProfile();
        disp_title.setText("Admin Details");
            if(adminProfileUser.getFirstname().equals("Admin")){
            disp_name.setText(adminProfileUser.getFirstname());
        }else{
            disp_name.setText(adminProfileUser.getFullName());
        }
        disp_calorie_title.setText("Clients");
        disp_calorie.setText(""+adminProfileUser.getNumberOfClient());

        disp_num_meal_title.setText("Meal Plans");
        disp_num_meal.setText(""+adminProfile.getAdminMealPlans().getNumber_of_mealplans());

        disp_age.setText("Age: "+adminProfileUser.getAge());
        disp_gender.setText("Gender: "+adminProfileUser.getGender());
        disp_goal.setText("Email: "+adminProfileUser.getEmail());
        progressView.setVisibility(View.GONE);
        disp_num_day_title.setVisibility(View.GONE);
        disp_num_days.setVisibility(View.GONE);
        edit_prof.setVisibility(View.GONE);

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
                Intent go = new Intent(getContext(), AddMeal.class);
                startActivity(go);
            }
        });

    }

    private void clientConfiguration(){
        Calendar c = Calendar.getInstance();
        Client clientProfile = ClientSystem.getClientProfile().getClientDetail();
        Date today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        edit_prof.setVisibility(View.VISIBLE);
        add_user.setVisibility(View.GONE);
        addMealPlan.setVisibility(View.GONE);
        disp_name.setText(clientProfile.getFullName());
        Consumption todayConsumption = ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today);
        if(todayConsumption != null){
            disp_calorie.setText(""+todayConsumption.getTotalCalorie());
            disp_num_meal.setText(""+todayConsumption.getMeal_list().size());
        }else{
            disp_calorie.setText("0");
            disp_num_meal.setText("0");
        }
        disp_num_days.setText(""+ (c.get(Calendar.DAY_OF_MONTH) - clientProfile.getCreation_date().getDay()));
        disp_age.setText("Age: "+clientProfile.getAge());
        disp_gender.setText("Gender: "+clientProfile.getGender());
        disp_goal.setText("Goal: "+ClientSystem.getClientProfile().getWorkOutPlan().getExpected_weight()+" lb");
        float progress = Float.parseFloat(""+ClientSystem.getClientProfile().getProgressReport().getProgrssPercentage());
        progressView.setValue( progress);


    }
}
