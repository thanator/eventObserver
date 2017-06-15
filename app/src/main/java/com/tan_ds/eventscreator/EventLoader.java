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
import android.util.EventLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventLoader extends AsyncTaskLoader<List<Event>> {

    private final EventContentObserver mEventObserver;

    public EventLoader(Context context) {
        super(context);
        mEventObserver = new EventContentObserver();
        context.getContentResolver().registerContentObserver(
                CalendarContract.Events.CONTENT_URI, false, mEventObserver
        );
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        getContext().getContentResolver().unregisterContentObserver(mEventObserver);
    }

    @Override
    public List<Event> loadInBackground() {

        List<Event> events = new ArrayList<>();
        Cursor cursor = getEventCursor();
        if (cursor != null){
            /////////////////////////////////////////////////////////
            cursor.close();
        }




        return events;
    }

    private Cursor getEventCursor(){
        ContentResolver resolver = getContext().getContentResolver();

        Cursor cursor = null;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED){
            cursor = resolver.query(
              CalendarContract.Events.CONTENT_URI, null, null, null, null
            );
        }
        return cursor;
    }

    private class EventContentObserver extends ContentObserver{

        public EventContentObserver() {
            super(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            onContentChanged();
        }
    }

}
