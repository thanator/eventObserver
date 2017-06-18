package com.tan_ds.eventscreator.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.tan_ds.eventscreator.Event;
import com.tan_ds.eventscreator.EventLoader;
import com.tan_ds.eventscreator.EventRecyclerViewAdapter;
import com.tan_ds.eventscreator.R;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EventRecyclerViewAdapter mAdapter;
    private static final int LOADER_ID = 1, PERMISSION_REQUEST_CODE = 1;

    private final static int UPDATE = 1, ADD = 0;
    public final static String EVENT = "10";



    public final static String POSITION = "11";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkEventPermission();

        mAdapter = new EventRecyclerViewAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, DoSomeThingWithEventActivity.class);
                startActivityForResult(intent, ADD);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkEventPermission();

    }

    private void checkEventPermission(){
        int permissionResultReader = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR);
        int permissionResultWriter = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);

        if (permissionResultReader == PackageManager.PERMISSION_GRANTED &&
                permissionResultWriter == PackageManager.PERMISSION_GRANTED){
            getSupportLoaderManager().initLoader(LOADER_ID, null, new EventLoaderCallbacks());
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR},
                    PERMISSION_REQUEST_CODE);
        }
    }


    private class EventLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Event>>{
        @Override
        public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
            return new EventLoader(EventActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
            Log.e("Shit", "events = "+data);
            mAdapter.remakeEvents(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Event>> loader) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode == RESULT_OK){
           if (requestCode == ADD){
                final Event event = (Event) data.getSerializableExtra(EVENT);
                mAdapter.setEvent(event);
                addEvent(event);
           }
       }
    }

    private void addEvent(Event event){
        ContentResolver contentResolver = this.getContentResolver();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CalendarContract.Events.DTSTART, event.getDate_from());
        contentValues.put(CalendarContract.Events.DTEND, event.getDate_to());
        contentValues.put(CalendarContract.Events.TITLE, event.getName());
        contentValues.put(CalendarContract.Events.DESCRIPTION, event.getWhat_to_do());
        contentValues.put(CalendarContract.Events.CALENDAR_ID, event.getCalendarId());
        contentValues.put(CalendarContract.Events.EVENT_TIMEZONE, event.getTimeZone());
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
            contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
