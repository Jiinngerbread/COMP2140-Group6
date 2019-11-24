package com.softeng_grup6.vainsfitness.systems;

import com.softeng_grup6.vainsfitness.utils.Profile;

public class ClientSystem {
    private Profile clientProfile= null;

    public ClientSystem(Profile clientProfile) {
        this.clientProfile = clientProfile;
    }

    public Profile getClientProfile() {
        return clientProfile;
    }
}
