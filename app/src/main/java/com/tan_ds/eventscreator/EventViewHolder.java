package com.tan_ds.eventscreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView mNameEvent, mWhatToDoOnEvent, mWhenToDoBeginEvent, mWhenToDoEndEvent;
    private Context mContext;

    public EventViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mNameEvent = (TextView) itemView.findViewById(R.id.event_name);
        mWhatToDoOnEvent = (TextView) itemView.findViewById(R.id.what_to_do_event);
        mWhenToDoBeginEvent = (TextView) itemView.findViewById(R.id.when_to_do_begin_event);
        mWhenToDoEndEvent = (TextView) itemView.findViewById(R.id.when_to_do_end_event);
    }

    public void bindView (Event event){
        mNameEvent.setText(event.getName());
        mWhatToDoOnEvent.setText(event.getWhat_to_do());
        Date dateFrom, dateTo;
        dateFrom = new Date(event.getDate_from());
        dateTo = new Date(event.getDate_to());
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);
        mWhenToDoBeginEvent.setText(dateFormat.format(dateFrom));
        mWhenToDoEndEvent.setText(dateFormat.format(dateTo));
    }
}
