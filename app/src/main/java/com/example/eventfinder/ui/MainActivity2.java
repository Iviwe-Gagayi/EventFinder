//package com.example.eventfinder.ui;
//
//import android.os.Bundle;
//import android.view.inputmethod.EditorInfo;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.eventfinder.R;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.ArrayList;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view0);
//
//        //dummy data for now - you ca rrplace
//       ArrayList<EventCardItem> events = new ArrayList<>();
////
////        events.add(new EventCardItem("Jazz Under the Stars"));
////        events.add(new EventCardItem("Soweto Food Festival"));
////        events.add(new EventCardItem("Wits Campus Derby"));
////        events.add(new EventCardItem("Origins Art Exhibition"));
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(
//                this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setAdapter(new MainPageAdapter(events));
//
//        navBar_activity.setup(this, "home");
//
//        TextInputEditText searchInput = findViewById(R.id.searchInput);
//
//        //backend search functionality
//        searchInput.setOnEditorActionListener((v, actionId, event) -> {
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                String query = searchInput.getText().toString().trim();
//                //put in the search backend stuff here --- query is what the user has typed in the search bar
//                if (!query.isEmpty()) {
//                    Toast.makeText(this, "Searching: " + query, Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//            return false;
//        });
//
//
//    }
//
//
//
//
//
//
//}