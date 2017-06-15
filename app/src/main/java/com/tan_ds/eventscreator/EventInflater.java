package com.tan_ds.eventscreator;

import android.database.Cursor;
import android.provider.CalendarContract;

import java.util.List;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventInflater {

    public static void fillList(Cursor source, List<Event> target){
        if (source.moveToFirst()){
            while (!source.isAfterLast()){
                target.add(createEventFromCursor(source));
                source.moveToNext();
            }
        }
    }

    private static Event createEventFromCursor(Cursor cursor){
        Event event = new Event();
        event.id = getLong(cursor, CalendarContract.Events._ID);
        event.name = getString(cursor, CalendarContract.Events.TITLE);
        event.date_from = getInt(cursor, CalendarContract.Events.DTSTART);
        event.date_to = getInt(cursor, CalendarContract.Events.DTEND);
        event.what_to_do = getString(cursor, CalendarContract.Events.DESCRIPTION);
        return event;
    }

    private static long getLong(Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private static int getInt(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }


    private static String getString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}
