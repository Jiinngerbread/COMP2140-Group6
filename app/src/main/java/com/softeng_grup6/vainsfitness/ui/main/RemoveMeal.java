package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Consumption;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Meal;

import java.util.Calendar;

public class RemoveMeal extends AppCompatActivity {
    private TextView eventTitle = null;
    private Button removeButton = null;
    private Button back = null;
    public static UserAcntHandler userAcntHandler = new UserAcntHandler();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removemeal);
        eventTitle = (TextView)findViewById(R.id.eventTitle);
        removeButton = (Button)findViewById(R.id.remove);
        back = (Button)findViewById(R.id.back);
        if(UserInterfaceManager.getLoggedInUserType() == "admin"){
            adminConfig();
        }else{
            clientCOnfig();
        }

    }
    public void adminConfig(){

    }

    public void clientCOnfig(){
        Calendar c = Calendar.getInstance();
        final Date today = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        Consumption consumption = ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today);
        if(consumption != null){
            Meal meal =consumption.getMeal_list().get(ConsumptionList.maealPosition);
            eventTitle.setText("Remove "+meal.getName()+" Meal");
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClientSystem.getClientProfile().getUserConsumption().getTodaysConsumption(today)
                            .removeMeal(ConsumptionList.maealPosition);
                    NetworkManager session = new NetworkManager();
                    session.clientConsumptionUpdate("rm",ClientSystem.getClientProfile().getUserConsumption());
                    userAcntHandler.setConsumptionUpdateListener(new NetSessionListener() {
                        @Override
                        public void succees() {
                            Toast.makeText(RemoveMeal.this, "Deleted!", Toast.LENGTH_SHORT).show();
                            returnHome();
                        }

                        @Override
                        public void unsuccessful() {
                            Toast.makeText(RemoveMeal.this, "Network Problem!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }

    public void returnHome(){
        userAcntHandler.setConsumptionUpdateListener(null);
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        finish();
    }
}
