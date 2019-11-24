package com.softeng_grup6.vainsfitness.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.utils.CalorieAPI;

import java.util.ArrayList;

public class tab1Fragment extends Fragment {
    private static TextView text = null;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        text = view.findViewById(R.id.txt);
        CalorieAPI cal = new CalorieAPI(getContext());

        ArrayList<String> items = new ArrayList<>();
        items.add("2 mangoes");
        cal.getCalorie("tab1",items);
        changeText("No response");
        return view;
    }

    public static void changeText(String txt){
        text.setText(txt);
    }


}
