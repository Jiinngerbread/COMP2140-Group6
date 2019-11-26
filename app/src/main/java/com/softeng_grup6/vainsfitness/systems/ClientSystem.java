package com.softeng_grup6.vainsfitness.systems;

import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.utils.Profile;
import com.softeng_grup6.vainsfitness.utils.User;

public class ClientSystem {
    private static Profile clientProfile= null;

    public static Profile getClientProfile() {
        return clientProfile;
    }

    public static void setClientProfile(Profile clientProfile) {
        ClientSystem.clientProfile = clientProfile;
    }
}
