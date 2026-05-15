package com.example.eventfinder.ui;

import java.util.ArrayList;

public class EventCardItem {
    private int eventId;
    private String title;
    private String description;
    private String eventDate;
    private double latitude;
    private double longitude;
    private int creatorId;

    private ArrayList<Integer> categoryIds;


    public EventCardItem(int eventId, String title, String description, String eventDate, double latitude, double longitude, int creatorId, ArrayList<Integer> categoryIds) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creatorId = creatorId;
        this.categoryIds = categoryIds;

    }


    public int getEventId() { return eventId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getEventDate() { return eventDate; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public int getCreatorId() { return creatorId; }
    public ArrayList<Integer> getCategoryIds() {
        return categoryIds;
    }
    public void setCategoryIds(ArrayList<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}