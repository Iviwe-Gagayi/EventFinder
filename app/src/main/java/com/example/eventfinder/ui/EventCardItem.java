package com.example.eventfinder.ui;

public class EventCardItem {
    private int eventId;
    private String title;
    private String description;
    private String eventDate;
    private double latitude;
    private double longitude;
    private int creatorId;


    public EventCardItem(int eventId, String title, String description, String eventDate, double latitude, double longitude, int creatorId) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creatorId = creatorId;
    }


    public int getEventId() { return eventId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getEventDate() { return eventDate; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public int getCreatorId() { return creatorId; }
}