package com.softeng_grup6.vainsfitness.listeners;

import com.google.firebase.firestore.DocumentReference;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.User;

public interface AuthListener {
    void onSuccess(DocumentReference id, Admin admin, Client client);
    void onfailed();
    void onlogin();
}
