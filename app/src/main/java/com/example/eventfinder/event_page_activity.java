package com.example.eventfinder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class event_page_activity extends AppCompatActivity {

    Button rsvp_btn;
    ImageView save_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_page);
        rsvp_btn = findViewById(R.id.RSVP_button);
        save_btn = findViewById(R.id.save_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton back_btn = findViewById(R.id.bck_btn);

        rsvp_btn.setOnClickListener(v -> {
            // add to db
            Toast.makeText(this, "You've RSVP'd!", Toast.LENGTH_SHORT).show();
            rsvp_btn.setText("RSVP'd ✓");
            rsvp_btn.setTextColor(getResources().getColor(R.color.white));
            rsvp_btn.setEnabled(false);
        });


        save_btn.setOnClickListener(v -> {
            // db stuff here
            Toast.makeText(this, "Event saved!", Toast.LENGTH_SHORT).show();
            save_btn.setImageResource(R.drawable.bookmark_filled);  // filled bookmark
        });



        back_btn.setOnClickListener(v -> finish());


    }
}