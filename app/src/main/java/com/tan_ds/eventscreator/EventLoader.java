package com.tan_ds.eventscreator;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.tan_ds.eventscreator.Model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventLoader extends AsyncTaskLoader<List<Event>> {

    public EventLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    public List<Event> loadInBackground() {

        List<Event> events = new ArrayList<>();

        Cursor cursor = getEventCursor();
        if (cursor != null){
            EventInflater.fillList(cursor, events);
            cursor.close();
        }
        return events;
    }

    private Cursor getEventCursor(){
        ContentResolver resolver = getContext().getContentResolver();

        Cursor cursor = null;
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED){
            cursor = resolver.query(
              CalendarContract.Events.CONTENT_URI, null, null, null, null
            );
        }
        return cursor;
    }
}
