package com.example.eventfinder.ui;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.eventfinder.R;


public class my_preferences_activity extends AppCompatActivity {

    private SwitchCompat musicSwitch, sportsSwitch, techAndGamingSwitch, festivalsSwitch, communitySwitch, artAndFilmSwitch, workshopSwitch;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences_page);
        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(v -> finish());

        musicSwitch = findViewById(R.id.music_preference);
        sportsSwitch = findViewById(R.id.sports_preference);
        techAndGamingSwitch = findViewById(R.id.tech_and_gaming_preference);
        festivalsSwitch = findViewById(R.id.festivals_preference);
        communitySwitch = findViewById(R.id.community_preference);
        artAndFilmSwitch = findViewById(R.id.art_and_film_preference);
        workshopSwitch = findViewById(R.id.workshop_preference);

        //insert backend stuff
    }
}