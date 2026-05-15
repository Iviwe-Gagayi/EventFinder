package com.example.eventfinder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;

public class RS_event_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rs_event_page);

        // Back button
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // RSVP'ed RecyclerView
        RecyclerView rsvpRecyclerView = findViewById(R.id.rsvpRecyclerView);
        List<EventCardItem> rsvpEvents = new ArrayList<>();

        // replace with the actual events that the user has rsvp'ed - you can remove the dummy data
        rsvpEvents.add(new EventCardItem("Global Bollywood Dance Fest"));
        rsvpEvents.add(new EventCardItem("Dlala Nje Ponte Stair Challenge"));
        rsvpEvents.add(new EventCardItem("Global Bollywood Dance Fest"));
        rsvpEvents.add(new EventCardItem("Dlala Nje Ponte Stair Challenge"));
        rsvpRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        rsvpRecyclerView.setAdapter(new RSEventAdapter(rsvpEvents));


        // Saved RecyclerView
        RecyclerView savedRecyclerView = findViewById(R.id.savedRecyclerView);
        List<EventCardItem> savedEvents = new ArrayList<>();
        // replace with the actual events that the user saves - you can remove the dummy data

        savedEvents.add(new EventCardItem("J. Cole Live"));
        savedEvents.add(new EventCardItem("Jazz Night"));
        savedEvents.add(new EventCardItem("Global Bollywood Dance Fest"));
        savedEvents.add(new EventCardItem("Dlala Nje Ponte Stair Challenge"));
        savedRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        savedRecyclerView.setAdapter(new RSEventAdapter(savedEvents));
    }




}
