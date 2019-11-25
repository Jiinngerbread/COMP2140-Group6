package com.softeng_grup6.vainsfitness.listeners;

import com.softeng_grup6.vainsfitness.utils.User;

public class UserAcntHandler {
    private UserAcntListener userAcntListener;

    public void setAccountInitializeListener(UserAcntListener userAcntListener){
        this.userAcntListener = userAcntListener;
    }

    public void initializeNewUser(User user){
        userAcntListener.onUserInitialize(user);
    }

}
