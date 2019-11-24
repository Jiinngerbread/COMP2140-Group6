package com.softeng_grup6.vainsfitness.utils;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.ui.main.tab1Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public class CalorieAPI {
    private String url = "https://api.edamam.com/api/nutrition-details";
    private String app_key = "7a1f9ce7b5c4183e5c347831225d386b";
    private String app_id = "79e932c0";
    private final Context context;
    public static String text_r = "fsd";
    RequestQueue queue;


    public CalorieAPI(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void getCalorie(final String from, ArrayList<String> food_items) {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkManager.getRetrofitClient();
        //The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        CalorieAPint calorieAPint = retrofit.create(CalorieAPint.class);
        //Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        JsonObject jsonbody = new JsonObject();
        jsonbody.addProperty("title", "food_item");
        JsonArray foodItems = new JsonArray();
        for(int a =0; a<food_items.size(); a++){ foodItems.add(food_items.get(a)); }
        jsonbody.add("ingr", foodItems);

        Call call = calorieAPint.getCalorie(jsonbody,app_id, app_key);

        //This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(from.equals("tab1")) {
                    Toast.makeText(context, "something:" + response.code() + " message: " + response.message(), Toast.LENGTH_SHORT).show();
                    Calories calories = (Calories) response.body();
                    tab1Fragment.changeText(calories.getCalories().toString());

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Error is " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

class Calories{
    @SerializedName("calories")
    @Expose
    private Integer calories;

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}

interface CalorieAPint{
    @Headers("Content-Type: application/json")
    @POST("/api/nutrition-details")
    Call< Calories > getCalorie(@Body JsonObject body, @Query("app_id") String appid, @Query("app_key") String apiKey);
}



