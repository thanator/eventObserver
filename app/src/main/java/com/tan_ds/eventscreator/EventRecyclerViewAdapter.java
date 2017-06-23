package com.tan_ds.eventscreator;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tan_ds.eventscreator.Activities.EventActivity;
import com.tan_ds.eventscreator.Fragments_Dialogs.ChoosingWhatToDoDialog;
import com.tan_ds.eventscreator.Model.Event;

import java.util.ArrayList;
import java.util.List;

import static com.tan_ds.eventscreator.VeryGlobalVariables.VICTIM;

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
    public void onBindViewHolder(final EventViewHolder eventViewHolder, final int i) {

        eventViewHolder.bindView(mEvents.get(i));

        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), ""+ i, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putInt(VICTIM, i);
                DialogFragment dialogFragment = new ChoosingWhatToDoDialog();
                dialogFragment.setArguments(args);
                dialogFragment.show(((EventActivity)eventViewHolder.itemView.getContext()).getThisShit(), "Choosing");
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
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
