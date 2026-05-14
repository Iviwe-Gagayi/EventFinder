//package com.example.eventfinder;
//
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.EventViewHolder> {
//
//    private List<EventCardItem> EventItems;
//
//    public MainPageAdapter(List<EventCardItem> mainPageItems) {
//        this.EventItems = mainPageItems;
//    }
//
//    @NonNull
//    @Override
//    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.event_card, parent, false);
//        return new EventViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
//        EventCardItem mainPageItem = EventItems.get(position);
//        holder.eventName.setText(mainPageItem.getEventName());
//
//        // open event detail page when user taps card
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), post_event_activity.class);
//            // pass event id to event detail page
//            // intent.putExtra("event_id", mainPageItem.getEventId());
//            v.getContext().startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return EventItems.size();
//    }
//
//    public static class EventViewHolder extends RecyclerView.ViewHolder {
//        TextView eventName;
//
//        public EventViewHolder(View itemView) {
//            super(itemView);
//            eventName = itemView.findViewById(R.id.event_name);
//        }
//
//
//    }
//
//
//
//
//}
