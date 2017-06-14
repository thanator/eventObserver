package com.tan_ds.eventscreator;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class EventLoader extends AsyncTaskLoader<List<Event>> {



    public EventLoader(Context context) {
        super(context);
    }

    @Override
    public List<Event> loadInBackground() {
        return null;
    }

}
