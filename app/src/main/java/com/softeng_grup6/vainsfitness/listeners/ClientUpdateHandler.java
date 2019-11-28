package com.softeng_grup6.vainsfitness.listeners;

public class ClientUpdateHandler {
    private  NetSessionListener netSessionListener;
    public void setClientUpdateListener(NetSessionListener netSessionListener){
        this.netSessionListener = netSessionListener;
    }
    public void clientUpdateSuccess(){
        netSessionListener.succees();
    }
    public void clientUpdateFail(){
        netSessionListener.unsuccessful();
    }
}
