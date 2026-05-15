package com.example.eventfinder.ui;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventfinder.R;

public class EventDetails extends AppCompatActivity {

    private TextView titleText;
    private TextView descriptionText;
    private TextView dateText;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_page);

        descriptionText = findViewById(R.id.event_description);
        dateText = findViewById(R.id.event_date);


        int eventId = getIntent().getIntExtra("event_id", -1);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String eventDate = getIntent().getStringExtra("event_date");


        if (description != null && descriptionText != null) {
            descriptionText.setText(description);
        }

        if (eventDate != null && dateText != null) {
            // TODO: show this nicely
            dateText.setText(eventDate);
        }
    }
}
