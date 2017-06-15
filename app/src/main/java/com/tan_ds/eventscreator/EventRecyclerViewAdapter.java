package com.tan_ds.eventscreator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {

    List <Event> mEvents;

    public EventRecyclerViewAdapter(){
        this.mEvents = new ArrayList<>();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_elem, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int i) {

        eventViewHolder.bindView(mEvents.get(i));

        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void remakeEvents(List<Event> events){
        mEvents.clear();
        mEvents.addAll(events);
        notifyDataSetChanged();
    }

    public void setEvent(Event event){
        mEvents.add(event);
        notifyItemInserted(mEvents.size() - 1);
        notifyDataSetChanged();
    }
    public void deleteEvent (int pos){
        mEvents.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }
    public void updateEvent(Event event, int pos){
        mEvents.set(pos, event);
        notifyDataSetChanged();
    }
}
