package com.example.eventfinder;

public class EventCardItem {
    private String EventName;
    private int rsvpCount; //to count number of rsvps for created events by the user

    //contrcutor for normal cards
    public EventCardItem(String name) {
        this.EventName = name;
        this.rsvpCount = 0;
    }

    //constructor fir created/ my events cards
    public EventCardItem(String name, int rsvpCount) {
        this.EventName = name;
        this.rsvpCount = rsvpCount;
    }

    public String getEventName() {
        return EventName;
    }

    public int getRsvpCount() {
        return rsvpCount;
    }
}