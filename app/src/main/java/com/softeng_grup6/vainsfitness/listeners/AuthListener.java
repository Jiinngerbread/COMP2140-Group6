package com.softeng_grup6.vainsfitness.listeners;

import com.google.firebase.firestore.DocumentReference;
import com.softeng_grup6.vainsfitness.utils.User;

public interface AuthListener {
    void onSuccess(DocumentReference id, User user);
    void onfailed();
    void onlogin(User user);
}
