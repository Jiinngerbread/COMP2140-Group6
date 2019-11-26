package com.softeng_grup6.vainsfitness.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.softeng_grup6.vainsfitness.R;

public class RemoveMeal extends AppCompatActivity {
    private TextView eventTitle = null;
    private Button removeButton = null;
    private Button back = null;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removemeal);
        eventTitle = (TextView)findViewById(R.id.eventTitle);
        removeButton = (Button)findViewById(R.id.remove);
        back = (Button)findViewById(R.id.back);
    }
}
