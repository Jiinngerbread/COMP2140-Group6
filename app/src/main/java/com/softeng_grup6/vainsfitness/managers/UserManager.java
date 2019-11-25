package com.softeng_grup6.vainsfitness.managers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.listeners.AuthHandler;
import com.softeng_grup6.vainsfitness.listeners.AuthListener;
import com.softeng_grup6.vainsfitness.ui.main.Login;
import com.softeng_grup6.vainsfitness.utils.User;

public class UserManager {
    private User user;
    private Context context;
    public static AuthHandler loginhandler = new AuthHandler();

    public UserManager(Context context) {
        this.context = context;
    }
    public UserManager(){}

    public void authenticate(final String email, final String password){
        final AuthHandler authHandler = new AuthHandler();
        final DocumentReference[] userId = new DocumentReference[1];
        final NetworkManager network = new NetworkManager();


        network.getAdminUsers().whereEqualTo("email",email)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().size() >0){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                userId[0] = network.getAdminUsers().document(document.getId());
                                authHandler.userFound(true,userId,document.toObject(User.class));
                            }catch (Exception e){
                                Log.d("error",e.getMessage());
                            }
                        }
                    }else{
                        network.getClientUsers().whereEqualTo("email",email)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    if(task.getResult().size() >0 ){
                                        for (DocumentSnapshot document : task.getResult()) {
                                            try {
                                                userId[0] = network.getClientUsers().document(document.getId());
                                                authHandler.userFound(true,userId,document.toObject(User.class));
                                            }catch (Exception e){
                                                Toast.makeText(context, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }else{
                                        authHandler.userFound(false,userId,null);
                                    }
                                }else{ Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();}
                            }
                        });
                    }
                }else{Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show(); }
            }
        });

        authHandler.setAuthListener(new AuthListener() {
            @Override
            public void onSuccess(DocumentReference id,User user) {
                if(user.getPassword().equals(password)){
                    Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
                    user.setId(id);
                    loginhandler.login_user(user);
                }else{
                    Toast.makeText(context, "Incorrect password or username", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onfailed() {
                Toast.makeText(context, "Incorrect password or username", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onlogin(User user) {
            }

        });
    }
}

