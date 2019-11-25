package com.softeng_grup6.vainsfitness.listeners;


import com.google.firebase.firestore.DocumentReference;
import com.softeng_grup6.vainsfitness.utils.User;

public class AuthHandler {
    private AuthListener listener;
    private  AuthListener loginListener;
    public void setAuthListener(AuthListener authListener){
        this.listener = authListener;
    }

    public void setLoginListener(AuthListener loginListener){
        this.loginListener = loginListener;
    }

    public void userFound(boolean found, DocumentReference[] id, User user){
        if(found){
            listener.onSuccess(id[0], user);
        }else{
            listener.onfailed();
        }

    }
    public void login_user(User user){
        loginListener.onlogin(user);
    }
}
