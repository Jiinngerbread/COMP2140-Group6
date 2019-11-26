package com.softeng_grup6.vainsfitness.managers;

import android.util.Log;
import android.widget.Toast;

import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.ui.main.ProgressReport;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.MealPlan;
import com.softeng_grup6.vainsfitness.utils.Profile;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.User;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserInterfaceManager {
    private static String loggedInUserType ="";
    private static Admin adminuser;
    private static Client clientuser;

    public static String getLoggedInUserType() {
        return loggedInUserType;
    }

    public static void setLoggedInUserType(String loggedInUserType) {
        UserInterfaceManager.loggedInUserType = loggedInUserType;
    }


    public static Admin getAdminuser() {
        return adminuser;
    }

    public static void setAdminUser(Admin adminuser, MealPlanManager mealPlanManager) {
        UserInterfaceManager.adminuser = adminuser;
        UserInterfaceManager.loggedInUserType ="admin";
        AdminSystem.setAdminProfile(new Profile(UserInterfaceManager.adminuser,mealPlanManager));
    }

    public static Client getClientuser() {
        return clientuser;
    }

    public static void setClientuser(Client clientuser, MealPlanManager mealPlanManager, ConsumptionManager consumptionManager, Report report, WorkOutPlan workOutPlan) {
        UserInterfaceManager.clientuser = clientuser;
        UserInterfaceManager.loggedInUserType = "client";
        ClientSystem.setClientProfile(new Profile(UserInterfaceManager.clientuser, consumptionManager,report,workOutPlan));
        ClientSystem.getClientProfile().setAdminMealPlans(mealPlanManager);
    }
}
