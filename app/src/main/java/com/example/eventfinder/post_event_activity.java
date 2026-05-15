package com.example.eventfinder;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class post_event_activity extends AppCompatActivity {

    private Uri selectedEventImageUri;
    private ImageView eventImagePreview;
    private LinearLayout eventImagePlaceholder;
    private ActivityResultLauncher<PickVisualMediaRequest> eventImageLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.PickVisualMedia(),
                    uri -> {
                        if (uri != null) {
                            selectedEventImageUri = uri;
                            eventImagePreview.setVisibility(View.VISIBLE);
                            eventImagePlaceholder.setVisibility(View.GONE);
                            Glide.with(this).load(uri).into(eventImagePreview);
                        }
                    }
            );

    TextView rsvpCountText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_event_page);
        navBar_activity.setup(this, "post");

        EditText eventNameInput, locationInput, descriptionInput, dateDay, dateMonth, dateYear, ticketPriceInput;
        Spinner categorySpinner;
        Button btn_cancel, btn_post;
        eventNameInput = findViewById(R.id.eventNameInput);
        locationInput = findViewById(R.id.locationInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_post = findViewById(R.id.btn_post);
        ticketPriceInput = findViewById(R.id.ticketPriceInput);
        dateDay = findViewById(R.id.dateDay);
        dateMonth = findViewById(R.id.dateMonth);
        dateYear = findViewById(R.id.dateYear);

        eventImagePreview = findViewById(R.id.eventImagePreview);
        eventImagePlaceholder = findViewById(R.id.eventImagePlaceholder);
        FrameLayout eventImagePicker = findViewById(R.id.eventImagePicker);


        eventImagePicker.setOnClickListener(v -> {
            eventImageLauncher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });

        btn_post.setOnClickListener(v -> {
            String eventName= eventNameInput.getText().toString();
            String location= locationInput.getText().toString();
            String description= descriptionInput.getText().toString();
            String category= categorySpinner.getSelectedItem().toString();
            String date= dateDay.getText().toString() + "/" + dateMonth.getText().toString() + "/" + dateYear.getText().toString();
            String ticketPrice= ticketPriceInput.getText().toString();


            if (eventName.isEmpty() || location.isEmpty() || description.isEmpty() || category.isEmpty() || date.isEmpty() || ticketPrice.isEmpty() ) {
                Toast.makeText(this,
                        "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;}

            if (selectedEventImageUri == null) {
                Toast.makeText(this,
                        "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }

            //add to db

            Toast.makeText(this,
                    "Event posted!", Toast.LENGTH_SHORT).show();

        });

        btn_cancel.setOnClickListener(View ->{
            finish();
        });



        //check if the user is the creator of the event in order to show the rsvp count on event page
        // Do db stuff to check if user is creator and get rsvp count
        boolean isCreator = false;
        int rsvpCount = 0;
        rsvpCountText = findViewById(R.id.rsvpCountText);
        if (isCreator) {
            rsvpCountText.setVisibility(View.VISIBLE);
            rsvpCountText.setText(rsvpCount + " people RSVP'd");
        }

        }





}






