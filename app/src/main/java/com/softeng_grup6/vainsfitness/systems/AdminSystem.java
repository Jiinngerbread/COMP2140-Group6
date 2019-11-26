package com.softeng_grup6.vainsfitness.systems;

import com.softeng_grup6.vainsfitness.managers.MealPlanManager;
import com.softeng_grup6.vainsfitness.utils.Profile;

public class AdminSystem {
    private static Profile adminProfile= null;

    public static Profile getAdminProfile() {
        return adminProfile;
    }

    public static void setAdminProfile(Profile adminProfile){
        AdminSystem.adminProfile = adminProfile;
    }

}
