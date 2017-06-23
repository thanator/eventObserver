package com.tan_ds.eventscreator.Activities;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tan_ds.eventscreator.Fragments_Dialogs.MyDatePickerDialog;
import com.tan_ds.eventscreator.Model.Event;
import com.tan_ds.eventscreator.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;
import static com.tan_ds.eventscreator.VeryGlobalVariables.VICTIM;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class DoSomeThingWithEventActivity extends AppCompatActivity {

    private TextView mWhatToDoWithEvent , mDateFrom, mDateTo, mMonthFrom, mMonthTo, mDayFrom, mDayto;
    private EditText mWhatToDo, mNameEvent;
    private EditText[] mEditTexts;
    private Button mButton;
    private int mType, mVictim;
    private static final int ID = 1;
    private static final int DATE_FROM = 1, DATE_TO = 2;

    private final Calendar mCalendar = Calendar.getInstance(TimeZone.getDefault());

    private static Map<String, Integer> monthMap = new HashMap<String, Integer>(){{
        put("Январь", 0);
        put("Февраль", 1);
        put("Март", 2);
        put("Апрель", 3);
        put("Май", 4);
        put("Июнь", 5);
        put("Июль", 6);
        put("Август", 7);
        put("Сентябрь", 8);
        put("Октябрь", 9);
        put("Ноябрь", 10);
        put("Декабрь", 11);
    }};

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, DoSomeThingWithEventActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mType = intent.getIntExtra(TYPE, 0);
        mVictim = intent.getIntExtra(VICTIM, -1);

        setContentView(R.layout.activity_do_something_with_event);
        mWhatToDoWithEvent = (TextView) findViewById(R.id.what_to_do_with_event);
        mNameEvent = (EditText) findViewById(R.id.event_name_edit_text);
        mWhatToDo = (EditText) findViewById(R.id.what_to_do_edit_text);
        mDateFrom = (TextView) findViewById(R.id.year_from);
        mDateTo = (TextView) findViewById(R.id.year_to);
        mButton = (Button) findViewById(R.id.event_button);

        mMonthFrom = (TextView) findViewById(R.id.month_from);
        mMonthTo = (TextView) findViewById(R.id.month_to);
        mDayFrom = (TextView) findViewById(R.id.day_from);
        mDayto = (TextView) findViewById(R.id.day_to);


        mEditTexts = new EditText[] {mWhatToDo, mNameEvent};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }
        mDateTo.addTextChangedListener(new TextWatcherImpl());
        mDateFrom.addTextChangedListener(new TextWatcherImpl());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSOmeThing();
            }
        });

    }

    public void onclick_from(View view){
        DialogFragment dialogFragment = new MyDatePickerDialog();
        Bundle args = new Bundle();
        args.putInt(TYPE, DATE_FROM);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "date picker");
    }

    public void onclick_to(View view){
        DialogFragment dialogFragment = new MyDatePickerDialog();
        Bundle args = new Bundle();
        args.putInt(TYPE, DATE_TO);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "date picker");
    }


    private void doSOmeThing(){
        Event newEvent = new Event();

        ContentResolver contentResolver = DoSomeThingWithEventActivity.this.getContentResolver();
        Uri uri = CalendarContract.Events.CONTENT_URI;
        Cursor cursor = null;

        newEvent.setCalendarId(1);
        TimeZone timeZone = TimeZone.getDefault();
        String timeZoneID = timeZone.getID();
        newEvent.setTimeZone(timeZoneID);

        newEvent.setName(mNameEvent.getText().toString());
        newEvent.setWhat_to_do(mWhatToDo.getText().toString());
        int day = Integer.parseInt(mDayFrom.getText().toString());
        int month = monthMap.get(mMonthFrom.getText().toString());
        int year = Integer.parseInt(mDateFrom.getText().toString());


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        newEvent.setDate_from(calendar.getTimeInMillis());

       dateCalendarMaker(Integer.parseInt(mDateTo.getText().toString()),
                monthMap.get(mMonthTo.getText().toString()),
                Integer.parseInt(mDayto.getText().toString()));
        newEvent.setDate_to(mCalendar.getTimeInMillis());

        try {
            if (ActivityCompat.checkSelfPermission(DoSomeThingWithEventActivity.this,
                    Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                cursor = contentResolver.query(uri, new String[]{"MAX(_id) as max_id"}, null, null, "_id");
                if (cursor != null) {
                    cursor.moveToFirst();
                    final long maxId = cursor.getLong(cursor.getColumnIndex("max_id"));
                    newEvent.setId(maxId);
                }
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }

        Intent intent  = new Intent() ;
        intent.putExtra(EventActivity.EVENT, newEvent);

        if (mType == 1){
            intent.putExtra(VICTIM, mVictim);

        }

        setResult(RESULT_OK, intent);

        finish();
    }

    private void dateCalendarMaker(int year, int month, int day){
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean buttonEnabled = true;
            for (EditText editText : mEditTexts) {
                if (TextUtils.isEmpty(editText.getText())) {
                    buttonEnabled = false;
                    break;
                }
            }
            if (mDateFrom.getText().equals(getText(R.string.dd_mm_yyyy))){
                buttonEnabled = false;
            }
            if (mDateTo.getText().equals(getText(R.string.dd_mm_yyyy))){
                buttonEnabled = false;
            }
            //TODO сделать когда-нибудь адекватную проверку на дату.
          /*  if (Integer.parseInt(mDateFrom.getText().toString()) > Integer.parseInt(mDateTo.getText().toString()) &&
                    !mDateTo.getText().toString().equals("1900")){
                buttonEnabled = false;

                Toast.makeText(getApplication().getBaseContext(), "Правая дата должна быть больше левой 1", Toast.LENGTH_SHORT).show();
            }
            if(monthMap.get(mMonthFrom.getText().toString()) > monthMap.get(mMonthTo.getText().toString()) &&
                    mDateFrom.getText().toString().equals(mDateTo.getText().toString()) &&
                    !mDateTo.getText().toString().equals("1900")){
                buttonEnabled = false;
                Toast.makeText(getApplication().getBaseContext(), "Правая дата должна быть больше левой 2", Toast.LENGTH_SHORT).show();
            }
            if(Integer.parseInt(mDayFrom.getText().toString()) > Integer.parseInt(mDayto.getText().toString()) &&
                    monthMap.get(mMonthFrom.getText().toString()) == monthMap.get(mMonthTo.getText().toString()) &&
                    Integer.parseInt(mDateFrom.getText().toString()) == Integer.parseInt(mDateTo.getText().toString()) &&
                    !mDateTo.getText().toString().equals("1900")){
                buttonEnabled = false;
                Toast.makeText(getApplication().getBaseContext(), "Правая дата должна быть больше левой 3", Toast.LENGTH_SHORT).show();
            }*/
          //// проверка на дату не работает. мб запилю потом.
          /// но ЭТОТ комментарий удалять пока не буду
          /// т.к. он - не баг, а фича. хоть и в будущем
            mButton.setEnabled(buttonEnabled);
        }

    }
}


