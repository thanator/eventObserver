package com.tan_ds.eventscreator;

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

    private TextView mWhatToDoWithEvent;
    private EditText mWhatToDo, mDateFrom, mDateTo;
    private EditText[] mEditTexts;
    private Button mButton;
    private int mType, mVictim;

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
        mWhatToDo = (EditText) findViewById(R.id.event_name_edit_text);
        mDateFrom = (EditText) findViewById(R.id.date_edit_from);
        mDateTo = (EditText) findViewById(R.id.date_edit_to);
        mButton = (Button) findViewById(R.id.event_button);


        mEditTexts = new EditText[] {mWhatToDo, mDateFrom, mDateTo};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fine();
            }
        });

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
            mButton.setEnabled(buttonEnabled);
        }
    }
}


