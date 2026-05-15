package com.example.eventfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eventfinder.adapter.MainPageAdapter;
import com.example.eventfinder.R;
import com.example.eventfinder.network.ApiClient;
import com.example.eventfinder.utils.SessionManager;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFeed extends AppCompatActivity {

    // global so whole page can use it
    private int currentUserId;
    private TextView greetingText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // checking if the user is logged in. If not direct them to the signup page
        currentUserId = SessionManager.getUserId(this);

        if (currentUserId == -1) {
            Toast.makeText(this, "Session expired. Please log in again.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
            finish();
            return;
        }

        //draw scree is user is logged in
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_feed);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        greetingText = findViewById(R.id.greeting_text);

        recyclerView = findViewById(R.id.recycler_view0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        fetchUserName();
        fetchEvents(0); //fetch everything is the default

        //category chips harcoding
        findViewById(R.id.chip_all).setOnClickListener(v -> fetchEvents(0));
        findViewById(R.id.chip_nightlife).setOnClickListener(v -> fetchEvents(1));
        findViewById(R.id.chip_tech).setOnClickListener(v -> fetchEvents(2));
        findViewById(R.id.chip_outdoors).setOnClickListener(v -> fetchEvents(3));
        findViewById(R.id.chip_campus_life).setOnClickListener(v -> fetchEvents(4));
        findViewById(R.id.chip_music).setOnClickListener(v -> fetchEvents(5));
        findViewById(R.id.chip_sport).setOnClickListener(v -> fetchEvents(6));
        findViewById(R.id.chip_festival).setOnClickListener(v -> fetchEvents(7));
        findViewById(R.id.chip_workshop).setOnClickListener(v -> fetchEvents(8));
        findViewById(R.id.chip_health).setOnClickListener(v -> fetchEvents(9));
        findViewById(R.id.chip_art).setOnClickListener(v -> fetchEvents(10));
        findViewById(R.id.chip_academic).setOnClickListener(v -> fetchEvents(11));
        findViewById(R.id.chip_fitness).setOnClickListener(v -> fetchEvents(12));
        findViewById(R.id.chip_gaming).setOnClickListener(v -> fetchEvents(13));
        findViewById(R.id.chip_food).setOnClickListener(v -> fetchEvents(14));
        findViewById(R.id.chip_networking).setOnClickListener(v -> fetchEvents(15));
    }

    private void fetchUserName() {
        String endpoint = "get_user.php?user_id=" + currentUserId;

        ApiClient.getInstance().getRequest(endpoint, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(MainFeed.this, "Failed to load profile", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();

                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonResponse = new JSONObject(responseData);
                            if (jsonResponse.getString("status").equals("success")) {
                                String name = jsonResponse.getJSONObject("data").getString("name");
                                greetingText.setText("Hello " + name + "!");
                            }
                        } catch (Exception e) {
                            Log.e("API_ERROR", "Error parsing name", e);
                        }
                    });
                }
            }
        });
    }

    private void fetchEvents(int categoryId) {
        //plaiceHolder coods for now
        //TODO: Get location working
        String endpoint = "get_events.php?user_id=" + currentUserId +
                "&lat=-26.1892&lng=28.0306&radius=50";


        if (categoryId != 0) {
            endpoint += "&category_id=" + categoryId;
        }

        ApiClient.getInstance().getRequest(endpoint, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(MainFeed.this, "Network error loading events", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();

                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonResponse = new JSONObject(responseData);
                            if (jsonResponse.getString("status").equals("success")) {

                                org.json.JSONArray eventsArray = jsonResponse.getJSONArray("data");
                                ArrayList<EventCardItem> eventList = new ArrayList<>();

                                // Loop through every event the database sent us
                                for (int i = 0; i < eventsArray.length(); i++) {
                                    JSONObject eventObj = eventsArray.getJSONObject(i);

                                    int eventId = eventObj.getInt("event_id");
                                    String eventTitle = eventObj.getString("title");
                                    String eventDesc = eventObj.getString("description");
                                    String eventDate = eventObj.getString("event_date");
                                    double lat = eventObj.getDouble("latitude");
                                    double lng = eventObj.getDouble("longitude");
                                    int creatorId = eventObj.getInt("creator_id");


                                    ArrayList<Integer> categoryIds = new ArrayList<>();


                                    if (eventObj.has("category_ids")) {
                                        org.json.JSONArray categoryArray = eventObj.getJSONArray("category_ids");
                                        for (int j = 0; j < categoryArray.length(); j++) {
                                            categoryIds.add(categoryArray.getInt(j));
                                        }
                                    }

                                    EventCardItem item = new EventCardItem(eventId, eventTitle, eventDesc, eventDate, lat, lng, creatorId, categoryIds);
                                    eventList.add(item);
                                }


                                MainPageAdapter adapter = new MainPageAdapter(eventList);
                                recyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(MainFeed.this, "Failed to load events", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("API_ERROR", "Error parsing events", e);
                        }
                    });
                }
            }
        });
    }
}