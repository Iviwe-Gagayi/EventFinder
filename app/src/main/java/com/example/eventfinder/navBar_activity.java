package com.example.eventfinder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class navBar_activity {

    public static void setup(Activity activity, String activeTab) {
         LinearLayout homeNav= activity.findViewById(R.id.Home_nav);
        LinearLayout mapNav =activity.findViewById(R.id.Map_nav);
        LinearLayout postNav= activity.findViewById(R.id.Post_nav);
        LinearLayout profileNav =activity.findViewById(R.id.Profile_nav);
        resetAll(activity);
        //transitions to the active tab, ignore
        switch (activeTab) {
            case "home":
                activate(activity, homeNav, R.id.Home_icon, R.id.Home_label);
                break;
            case "map":
                activate(activity, mapNav, R.id.Map_icon, R.id.Map_label);
                break;
            case "post":
                activate(activity, postNav, R.id.Post_icon, R.id.Post_label);
                break;
            case "profile":
                activate(activity, profileNav, R.id.Profile_icon, R.id.Profile_label);
                break;
        }

        //These allow the user to actually click on the navbar
        homeNav.setOnClickListener(v ->
                navigate(activity, MainActivity2.class, activeTab, "home"));

//        Still need to implement map page
//        mapNav.setOnClickListener(v ->
//                navigate(activity, MapActivity.class, activeTab, "map"));

        postNav.setOnClickListener(v ->
                navigate(activity, post_event_activity.class, activeTab, "post"));

        profileNav.setOnClickListener(v ->
                navigate(activity, profile_activity.class, activeTab, "profile"));
    }

    private static void resetAll(Activity activity) {



        activity.findViewById(R.id.Home_nav).setBackground(null);
        activity.findViewById(R.id.Map_nav).setBackground(null);
        activity.findViewById(R.id.Post_nav).setBackground(null);
        activity.findViewById(R.id.Profile_nav).setBackground(null);

        activity.findViewById(R.id.Home_label).setVisibility(View.GONE);
        activity.findViewById(R.id.Map_label).setVisibility(View.GONE);
        activity.findViewById(R.id.Post_label).setVisibility(View.GONE);
        activity.findViewById(R.id.Profile_label).setVisibility(View.GONE);

        ((ImageView) activity.findViewById(R.id.Home_icon))
                .setColorFilter(0xFF1a1a2e);
        ((ImageView) activity.findViewById(R.id.Map_icon))
                .setColorFilter(0xFF1a1a2e);
        ((ImageView) activity.findViewById(R.id.Post_icon))
                .setColorFilter(0xFF1a1a2e);
    }

    private static void activate(Activity activity, LinearLayout container,
                                 int iconId, int labelId) {
        container.setBackgroundResource(R.drawable.nav_active_pilll);
        activity.findViewById(labelId).setVisibility(View.VISIBLE);

        //design implementation, ignore
        View icon = activity.findViewById(iconId);
        if (icon instanceof ImageView &&
                !(icon instanceof de.hdodenhof.circleimageview.CircleImageView)) {
            ((ImageView) icon).setColorFilter(0xFFFFFFFF);
        }
    }

    // Also design implementation, ignore
    private static void navigate(Activity activity, Class<?> destination,
                                 String currentTab, String targetTab) {

        if (currentTab.equals(targetTab)) return;

        Intent intent = new Intent(activity, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }
}