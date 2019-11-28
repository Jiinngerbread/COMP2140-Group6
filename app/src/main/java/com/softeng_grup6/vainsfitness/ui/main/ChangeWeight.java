package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.ClientUpdateHandler;
import com.softeng_grup6.vainsfitness.listeners.NetSessionListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntHandler;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.User;

public class ChangeWeight extends AppCompatActivity {
    private EditText weightText;
    private Button submit;
    public static ClientUpdateHandler clientUpdateHandler = new ClientUpdateHandler();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_weight);
        weightText = (EditText)findViewById(R.id.changeWeightInput);
        submit = (Button)findViewById(R.id.UpdateWeightButton);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double newWeight = Double.parseDouble(weightText.getText().toString());
                if (newWeight > ClientSystem.getClientProfile().getProgressReport().getStartWeight()/4){
                    ClientSystem.getClientProfile().getProgressReport().generateReport(newWeight);
                    ClientSystem.getClientProfile().getClientDetail().setWeight(newWeight);
                    NetworkManager session = new NetworkManager();
                    session.setNetContext(getApplicationContext());
                    session.updateClient(
                            ClientSystem.getClientProfile().getClientDetail(),
                            ClientSystem.getClientProfile().getProgressReport(),
                            ClientSystem.getClientProfile().getWorkOutPlan(),
                            ClientSystem.getClientProfile().getUserConsumption());
                    ChangeWeight.clientUpdateHandler.setClientUpdateListener(new NetSessionListener() {
                        @Override
                        public void succees() {
                            Toast.makeText(ChangeWeight.this, "Updated Succesfully", Toast.LENGTH_SHORT).show();
                            ChangeWeight.clientUpdateHandler.setClientUpdateListener(null);
                            Intent go = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(go);
                            finish();
                        }

                        @Override
                        public void unsuccessful() {
                            Toast.makeText(ChangeWeight.this, "Network Problem", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(ChangeWeight.this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
