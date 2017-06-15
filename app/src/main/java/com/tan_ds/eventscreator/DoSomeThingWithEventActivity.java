package com.tan_ds.eventscreator;

import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;
import static com.tan_ds.eventscreator.VeryGlobalVariables.VICTIM;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class DoSomeThingWithEventActivity extends AppCompatActivity {

    private TextView mWhatToDoWithEvent , mDateFrom, mDateTo;
    private EditText mWhatToDo, mNameEvent;
    private EditText[] mEditTexts;
    private Button mButton;
    private int mType, mVictim;
    private static final int ID = 1;
    private static final int DATE_FROM = 1, DATE_TO = 2;

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
        mDateFrom = (TextView) findViewById(R.id.date_edit_from);
        mDateTo = (TextView) findViewById(R.id.date_edit_to);
        mButton = (Button) findViewById(R.id.event_button);


        mEditTexts = new EditText[] {mWhatToDo, mNameEvent};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }
        mDateTo.addTextChangedListener(new TextWatcherImpl());
        mDateFrom.addTextChangedListener(new TextWatcherImpl());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fine();
            }
        });

    }

    public void onclick_from(View view){
        DialogFragment dialogFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, DATE_FROM);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "date picker");
    }

    public void onclick_to(View view){
        DialogFragment dialogFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, DATE_TO);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "date picker");
    }


    private void fine(){


        finish();
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
            mButton.setEnabled(buttonEnabled);
        }
        private boolean whoIsOlder(String dateOne, String dateTwo){
            return true;
        }
    }
}


