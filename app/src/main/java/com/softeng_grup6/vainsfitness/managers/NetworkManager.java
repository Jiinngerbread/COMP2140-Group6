package com.softeng_grup6.vainsfitness.managers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.User;

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
    private final CollectionReference clientUsers = db.collection("Users")
            .document("client").collection("users");
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
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();
        }
        return retrofit;
    }

    public void addUser(){
        User user = new User("app","bapp",12,new Date(1,2,2019),"aaaa","password","c@c.com");
        Map<String, Object> users = new HashMap<>();
        users.put("count", 3);
        adminUsers.document("user2")
                .set(user);
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
}
