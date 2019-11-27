package com.softeng_grup6.vainsfitness.managers;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.DialogCompat;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.softeng_grup6.vainsfitness.systems.AdminSystem;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.ui.main.AddMeal;
import com.softeng_grup6.vainsfitness.ui.main.AddMealPlan;
import com.softeng_grup6.vainsfitness.ui.main.AddUser;
import com.softeng_grup6.vainsfitness.ui.main.RemoveMeal;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.MealPlan;
import com.softeng_grup6.vainsfitness.utils.Report;
import com.softeng_grup6.vainsfitness.utils.User;
import com.softeng_grup6.vainsfitness.utils.WorkOutPlan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    public static final String CAL_API_BASE_URL = "https://api.edamam.com";
    public static Retrofit retrofit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context netContext;
    private final CollectionReference adminUsers = db.collection("Users")
            .document("admin").collection("users");
    private final DocumentReference admindoc = db.collection("Users")
            .document("admin");
    private final CollectionReference clientUsers = db.collection("Users")
            .document("client").collection("users");
    private final DocumentReference clientdoc = db.collection("Users")
            .document("client");

    private final DocumentReference mealPlanlist = db.collection("MealPlans").document("mealplans");


    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public static Retrofit getRetrofitClient() {
        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(CAL_API_BASE_URL) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Converter library used to convert response into POJO
                    .build();
        }
        return retrofit;
    }

    public void addAdmin(){
        Admin admin = new Admin("Admin","Admin",30,new Date(26,11,2019),"admin","admin","admin@vainfitness.com","male",0,new ArrayList<String>());
        Map<String, Object> users = new HashMap<>();
        users.put("count", 3);
        adminUsers.document("main")
                .set(admin);
        admindoc.update("count",1);
    }

    public void setNetContext(Context netContext) {
        this.netContext = netContext;
    }

    public CollectionReference getAdminUsers() {
        return adminUsers;
    }

    public CollectionReference getClientUsers() {
        return clientUsers;
    }

    public void checkClientUsername(String username){
        clientUsers.whereEqualTo("username",username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().size() > 0){
                            AddUser.userAcntHandler.userUsed();
                        }else{
                            AddUser.userAcntHandler.userFree();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                AddUser.userAcntHandler.userUsed();
            }
        });
    }

    public void addNewClient(final Client client, final Report report, final WorkOutPlan workOutPlan){
        clientUsers.add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        DocumentReference id = clientUsers.document(documentReference.getId());
                        CollectionReference lClientDetail = id.collection("Details");
                        DocumentReference report_ref = lClientDetail.document("report");
                        DocumentReference workout_ref = lClientDetail.document("workoutplan");

                        report_ref.set(report);
                        workout_ref.set(workOutPlan);

                        AdminSystem.getAdminProfile().getAdminDetail().addClient(client.getUsername());
                        clientdoc.update("count",  AdminSystem.getAdminProfile().getAdminDetail().getNumberOfClient())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        adminUsers.document("main")
                                                .set( AdminSystem.getAdminProfile().getAdminDetail())
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        AddUser.userAcntHandler.addSucess();

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) { AddUser.userAcntHandler.addFail();}
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) { AddUser.userAcntHandler.addFail(); }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { AddUser.userAcntHandler.addFail(); }
        });
    }

    public void updateMealPlans(String from, MealPlanManager mealPlanManager){
        if(from.equals("amp")){
            mealPlanlist.set(mealPlanManager).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AddMeal.mealPlanHandler.mealPlanUpdateSuccessfull();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AddMeal.mealPlanHandler.mealPlanUpdateUnsuccessful();
                }
            });
        }else if(from.equals("rmp")){
            mealPlanlist.set(mealPlanManager).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    RemoveMeal.mealPlanHandler.mealPlanUpdateSuccessfull();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    RemoveMeal.mealPlanHandler.mealPlanUpdateUnsuccessful();
                }
            });
        }
    }

    public void loadMealPlans(){
        mealPlanlist.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getData() != null){
                            MealPlanManager mealPlanManager = documentSnapshot.toObject(MealPlanManager.class);
                            //documentSnapshot.toObject(MealPlanManager.class
                            UserManager.mealPlanHandler.mealPlanManagerLoadSuccessfull(mealPlanManager);
                        }else{
                            UserManager.mealPlanHandler.mealPlanManagerLoadSuccessfull(new MealPlanManager());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                UserManager.mealPlanHandler.mealPlanManagerLoadUnsuccessful();
            }
        });

    }
    public void loadClientDetails(DocumentReference id){
        final CollectionReference lClientDetail = id.collection("Details");
        final DocumentReference consumption_ref = lClientDetail.document("consumption");
        final DocumentReference report_ref = lClientDetail.document("report");
        final DocumentReference workout_ref = lClientDetail.document("workoutplan");
        final ConsumptionManager[] consumptionManager = {new ConsumptionManager()};
        final Report[] report = {new Report()};
        final WorkOutPlan[] workOutPlan = {new WorkOutPlan()};

        consumption_ref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getData() != null){
                            consumptionManager[0] = documentSnapshot.toObject(ConsumptionManager.class);
                        }
                        report_ref.get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.getData() != null){
                                            report[0] = documentSnapshot.toObject(Report.class);
                                        }
                                        workout_ref.get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if(documentSnapshot.getData() != null){
                                                            workOutPlan[0] = documentSnapshot.toObject(WorkOutPlan.class);
                                                        }
                                                        UserManager.userAcntHandler.clientLoadSucceed(consumptionManager[0],report[0],workOutPlan[0]);
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) { UserManager.userAcntHandler.clientLoadFail();}
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) { UserManager.userAcntHandler.clientLoadFail();}
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { UserManager.userAcntHandler.clientLoadFail(); }
        });
    }

    public void clientConsumptionUpdate(String  from, ConsumptionManager consumptionManager){
        DocumentReference consum_ref = ClientSystem.getClientProfile().getClientDetail().getId().collection("Details").document("consumption");
        if(from.equals("am")){
            consum_ref.set(consumptionManager).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AddMeal.userAcntHandler.consumptionUpdateSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AddMeal.userAcntHandler.consumptionUpdateFail();
                }
            });
        }else if (from.equals("rm")){
            consum_ref.set(consumptionManager).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    RemoveMeal.userAcntHandler.consumptionUpdateSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    RemoveMeal.userAcntHandler.consumptionUpdateFail();
                }
            });
        }else if (from.equals("amp")){
            consum_ref.set(consumptionManager).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AddMealPlan.userAcntHandler.consumptionUpdateSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AddMealPlan.userAcntHandler.consumptionUpdateFail();
                }
            });
        }
    }
}
