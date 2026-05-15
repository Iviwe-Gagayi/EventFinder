package com.example.eventfinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myEvents_activity extends AppCompatActivity {

    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_events);
        btn_back = findViewById(R.id.back_btn);
        btn_back.setOnClickListener(v -> finish());
        RecyclerView myEventsRecyclerView = findViewById(R.id.myEventsRecyclerView);

        // replace with the actual events that the user creates - you can remove the dummy data
        ArrayList<EventCardItem> myEvents = new ArrayList<>();
        myEvents.add(new EventCardItem("Jazz Night", 23));
        myEvents.add(new EventCardItem("Soweto Food Festival", 156));
        myEvents.add(new EventCardItem("Wits Campus Derby", 7));
        myEvents.add(new EventCardItem("Origins Art Exhibition", 0));



        myEventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        myEventsRecyclerView.setAdapter(new RSEventAdapter(myEvents));

    }
}