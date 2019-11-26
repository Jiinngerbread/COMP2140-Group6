package com.softeng_grup6.vainsfitness.utils;

import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.managers.MealPlanManager;

public class Profile {
    private Admin adminDetail = null;
    private Client clientDetail = null;
    private ConsumptionManager userConsumption = null;
    private Report progressReport = null;
    private WorkOutPlan workOutPlan = null;
    private MealPlanManager adminMealPlans = null;

    public Profile(Client clientDetail, ConsumptionManager userConsumption, Report progressReport, WorkOutPlan workOutPlan) {
        this.clientDetail = clientDetail;
        this.userConsumption = userConsumption;
        this.progressReport = progressReport;
        this.workOutPlan = workOutPlan;
    }

    public Profile(Admin adminDetail, Report progressReport, MealPlanManager adminMealPlans) {
        this.adminDetail = adminDetail;
        this.progressReport = progressReport;
        this.adminMealPlans = adminMealPlans;
    }

    public Profile(Admin adminDetail, MealPlanManager adminMealPlans) {
        this.adminDetail = adminDetail;
        this.adminMealPlans = adminMealPlans;
    }

    public Profile() {
    }

    public Admin getAdminDetail() {
        return adminDetail;
    }

    public Client getClientDetail() {
        return clientDetail;
    }

    public MealPlanManager getAdminMealPlans() {
        return adminMealPlans;
    }

    public void setAdminMealPlans(MealPlanManager adminMealPlans) {
        this.adminMealPlans = adminMealPlans;
    }

    public ConsumptionManager getUserConsumption() {
        return userConsumption;
    }

    public Report getProgressReport() {
        return progressReport;
    }

    public WorkOutPlan getWorkOutPlan() {
        return workOutPlan;
    }


}
