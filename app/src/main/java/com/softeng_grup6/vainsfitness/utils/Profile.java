package com.softeng_grup6.vainsfitness.utils;

import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;

public class Profile {
    private User userDetail = null;
    private ConsumptionManager userConsumption = null;
    private Report progressReport = null;
    private WorkOutPlan workOutPlan = null;

    public Profile(User userDetail, ConsumptionManager userConsumption, Report progressReport, WorkOutPlan workOutPlan) {
        this.userDetail = userDetail;
        this.userConsumption = userConsumption;
        this.progressReport = progressReport;
        this.workOutPlan = workOutPlan;
    }

    public User getUserDetail() {
        return userDetail;
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
