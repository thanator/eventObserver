package com.tan_ds.eventscreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tan_ds.eventscreator.Model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

           /*Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        newEvent.setDate_from(calendar.getTimeInMillis());
*/

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);

        mWhenToDoBeginEvent.setText(getDate(event.getDate_from()));
        mWhenToDoEndEvent.setText(dateFormat.format(dateTo));
    }

    private static String getDate(long millisec){
        Log.w("Holder: ", ""+millisec);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisec);
        return format.format(calendar.getTime());
    }
}
