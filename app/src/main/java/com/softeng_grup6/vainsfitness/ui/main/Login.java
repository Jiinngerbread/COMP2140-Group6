package com.softeng_grup6.vainsfitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.AuthHandler;
import com.softeng_grup6.vainsfitness.listeners.AuthListener;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.managers.UserManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Admin;
import com.softeng_grup6.vainsfitness.utils.Client;
import com.softeng_grup6.vainsfitness.utils.User;


public class Login extends AppCompatActivity {
    private EditText uname;
    private EditText pswd;
    private Button slogin;
    private UserManager authenticator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        uname = (EditText)findViewById(R.id.usrn);
        pswd = (EditText)findViewById(R.id.usrp);
        slogin = (Button)findViewById(R.id.login);
//        NetworkManager net = new NetworkManager();
//        net.addAdmin();
        authenticator = new UserManager(getBaseContext());
        //authenticator.authenticate("john","password");

        slogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                String password = pswd.getText().toString();
                authenticator.authenticate(username,password);
            }
        });

        UserManager.loginhandler.setLoginListener(new AuthListener() {
            @Override
            public void onSuccess(DocumentReference id, Admin admin, Client client) { }
            @Override
            public void onfailed() {}
            @Override
            public void onlogin() { login(); }
        });
    }
    public void login(){
        Intent go = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(go);
        UserManager.loginhandler.setLoginListener(null);
        finish();
    }
}
