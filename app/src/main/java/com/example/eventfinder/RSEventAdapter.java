package com.example.eventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RSEventAdapter extends RecyclerView.Adapter<RSEventAdapter.RSEventViewHolder> {

    private List<EventCardItem> events;

    public RSEventAdapter(List<EventCardItem> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public RSEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rsvp_saved_event_card, parent, false);
        return new RSEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RSEventViewHolder holder, int position) {
        // date and location should be set here
        EventCardItem event = events.get(position);
        holder.eventName.setText(event.getEventName());


        // shows badge iff the event has rsvpCount > 0
        int count = event.getRsvpCount();
        if (count > 0) {
            holder.rsvpBadge.setVisibility(View.VISIBLE);
            holder.rsvpBadge.setText(count + " RSVPs");
        } else {
            holder.rsvpBadge.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class RSEventViewHolder extends RecyclerView.ViewHolder {
        TextView eventName, eventDate, eventLocation, rsvpBadge;
        ImageView eventImage;

        public RSEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.rs_event_name);
            eventDate = itemView.findViewById(R.id.rs_event_date);
            eventLocation = itemView.findViewById(R.id.rs_event_location);
            eventImage = itemView.findViewById(R.id.rs_event_img);
            rsvpBadge = itemView.findViewById(R.id.rsvp_badge);
        }
    }


}