package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        LinearLayout acc_btn = findViewById(R.id.account_btn);
        GridLayout rs_btn = findViewById(R.id.rs_button);
        LinearLayout my_events_btn = findViewById(R.id.my_events_btn);
        LinearLayout my_preferences_btn = findViewById(R.id.my_preferences_btn);

        navBar_activity.setup(this, "profile");
        acc_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Account_activity.class);
            startActivity(intent);
        });
        rs_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RS_event_activity.class);
            startActivity(intent);
        });
        my_events_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, myEvents_activity.class);
            startActivity(intent);
        });
        my_preferences_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, my_preferences_activity.class);
            startActivity(intent);
        });


    }
}