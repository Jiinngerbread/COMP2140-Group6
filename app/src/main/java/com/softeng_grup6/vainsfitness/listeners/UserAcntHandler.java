package com.softeng_grup6.vainsfitness.listeners;

import com.softeng_grup6.vainsfitness.managers.ConsumptionManager;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.User;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

public class UserAcntHandler {
    private UserAcntCheckListener userAcntCheckListener;
    private NetSessionListener netSessionListener;
    private  ClientDetailListener clientDetailListener;
    private NetSessionListener netSessionListener2;
    private NetSessionListener clientUpdateListener;

    public void setAccountCheckListener(UserAcntCheckListener userAcntCheckListener){
        this.userAcntCheckListener = userAcntCheckListener;
    }

    public void userFree(){userAcntCheckListener.userAvailable();}
    public void userUsed(){userAcntCheckListener.userUnavailable();}

    public void setUserAccountAddListener(NetSessionListener netSessionListener){
        this.netSessionListener = netSessionListener;
    }
    public  void addSucess(){netSessionListener.succees();}
    public  void addFail(){netSessionListener.unsuccessful();}

    public void seLoadClientDetailsListener(ClientDetailListener clientDetailListener){
        this.clientDetailListener = clientDetailListener;
    };

    public void clientLoadSucceed(ConsumptionManager consumptionManager, Report report, WorkOutPlan workOutPlan){
        clientDetailListener.clientDetailLoadSuccessfully(consumptionManager,report,workOutPlan);
    }
    public  void clientLoadFail(){clientDetailListener.clientDetailLoadUnsuccessfully();}

    public void setConsumptionUpdateListener(NetSessionListener netSessionListener2){
        this.netSessionListener2 = netSessionListener2;
    }


    public void consumptionUpdateSuccess(){netSessionListener2.succees();}
    public void consumptionUpdateFail(){netSessionListener2.unsuccessful();}

    public void setClientUpdateListener(NetSessionListener clientUpdateListener){
        this.clientUpdateListener = clientUpdateListener;
    }
    public void clientUpdateSuccess(){
        clientUpdateListener.succees();
    }
    public void clientUpdateFail(){
        clientUpdateListener.unsuccessful();
    }


}
