package com.softeng_grup6.vainsfitness.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.softeng_grup6.vainsfitness.MainActivity;
import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.listeners.AuthListener;
import com.softeng_grup6.vainsfitness.listeners.UserAcntListener;
import com.softeng_grup6.vainsfitness.managers.NetworkManager;
import com.softeng_grup6.vainsfitness.managers.UserManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.CalorieAPI;
import com.softeng_grup6.vainsfitness.utils.User;

import java.util.ArrayList;

public class tab1Fragment extends Fragment {
    private static TextView text = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        text = view.findViewById(R.id.txt);
        CalorieAPI cal = new CalorieAPI(getContext());

        ArrayList<String> items = new ArrayList<>();
        items.add("2 mangoes");
        //cal.getCalorie("tab1",items);
        changeText("No response");
        NetworkManager net = new NetworkManager();
        net.setNetContext(getContext());
        //net.addUser();
        //UserManager auth = new UserManager(getContext());
        //auth.authenticate("c@c.com","password");
        Toast.makeText(getContext(), "Email for client: "+ClientSystem.getUser().getEmail(), Toast.LENGTH_SHORT).show();

        return view;
    }

    public static void changeText(String txt){
        text.setText(txt);
    }


}
