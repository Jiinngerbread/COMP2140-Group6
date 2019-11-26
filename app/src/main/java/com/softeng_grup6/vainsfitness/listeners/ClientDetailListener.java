package com.softeng_grup6.vainsfitness.listeners;

import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

public interface ClientDetailListener {
    void clientDetailLoadSuccessfully(ConsumptionManager consumptionManager, Report report, WorkOutPlan workOutPlan);
    void clientDetailLoadUnsuccessfully();
}
