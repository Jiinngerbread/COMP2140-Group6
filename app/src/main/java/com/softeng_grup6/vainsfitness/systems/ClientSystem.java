package com.softeng_grup6.vainsfitness.systems;

import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.utils.Profile;
import com.softeng_grup6.vainsfitness.utils.User;

public class ClientSystem {
    private Profile clientProfile= null;
    public static UserAcntHandler userAcntHandler = new UserAcntHandler();
    private static User user;

    public ClientSystem(Profile clientProfile) {
        this.clientProfile = clientProfile;
    }

    public Profile getClientProfile() {
        return clientProfile;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ClientSystem.user = user;
    }
}
