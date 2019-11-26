package com.softeng_grup6.vainsfitness.managers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.softeng_grup6.vainsfitness.listeners.AuthHandler;
import com.softeng_grup6.vainsfitness.listeners.AuthListener;
import com.softeng_grup6.vainsfitness.listeners.ClientDetailListener;
import com.softeng_grup6.vainsfitness.listeners.MealPlanHandler;
import com.softeng_grup6.vainsfitness.listeners.MealPlanListener;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.User;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

import java.util.ArrayList;

public class UserManager {
    private User user;
    private Context context;
    public static AuthHandler loginhandler = new AuthHandler();
    public static MealPlanHandler mealPlanHandler = new MealPlanHandler();
    public static UserAcntHandler userAcntHandler = new UserAcntHandler();
    private static ArrayList<Client> allClients = null;


    public UserManager(Context context) {
        this.context = context;
    }

    public UserManager(){}

    public void authenticate(final String email, final String password){
        final AuthHandler authHandler = new AuthHandler();
        final DocumentReference[] userId = new DocumentReference[1];
        final NetworkManager network = new NetworkManager();
        network.setNetContext(context);


        network.getAdminUsers().whereEqualTo("username",email)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().size() >0){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                userId[0] = network.getAdminUsers().document(document.getId());
                                authHandler.userFound(true,userId,document.toObject(Admin.class), null);
                            }catch (Exception e){
                                Log.d("error",e.getMessage());
                            }
                        }
                    }else{
                        network.getClientUsers().whereEqualTo("username",email)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    if(task.getResult().size() >0 ){
                                        for (DocumentSnapshot document : task.getResult()) {
                                            try {
                                                userId[0] = network.getClientUsers().document(document.getId());
                                                authHandler.userFound(true,userId,null,document.toObject(Client.class));
                                            }catch (Exception e){
                                                Toast.makeText(context, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }else{
                                        authHandler.userFound(false,userId,null, null);
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
            public void onSuccess(final DocumentReference id, final Admin admin, final Client client) {
                network.loadMealPlans();
                mealPlanHandler.setOnLoadMealPlanManager(new MealPlanListener() {
                    @Override
                    public void succees(final MealPlanManager mealPlanManager) {
                        if(admin!= null){
                            if(admin.getPassword().equals(password)){
                                admin.setId(id);
                                UserInterfaceManager.setAdminUser(admin, mealPlanManager);
                                loginhandler.login_user();
                            }else{
                                Toast.makeText(context, "Incorrect password or username", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            if(client.getPassword().equals(password)){
                                client.setId(id);
                                network.loadClientDetails(id);
                                userAcntHandler.seLoadClientDetailsListener(new ClientDetailListener() {
                                    @Override
                                    public void clientDetailLoadSuccessfully(ConsumptionManager consumptionManager, Report report, WorkOutPlan workOutPlan) {
                                        UserInterfaceManager.setClientuser(client, mealPlanManager,consumptionManager,report,workOutPlan);
                                        loginhandler.login_user();
                                    }
                                    @Override
                                    public void clientDetailLoadUnsuccessfully() {
                                        Toast.makeText(context, "Network Problem", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                Toast.makeText(context, "Incorrect password or username", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void unsuccessful() {
                        Toast.makeText(context, "Network Problem", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @Override
            public void onfailed() {
                Toast.makeText(context, "Incorrect password or username", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onlogin() { }
        });
    }

    public static ArrayList<Client> getAllClients() {
        return allClients;
    }

    public static void setAllClients(ArrayList<Client> allClients) {
        UserManager.allClients = allClients;
    }
}

