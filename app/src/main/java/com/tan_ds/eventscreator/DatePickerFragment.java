package com.tan_ds.eventscreator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private int mType;

    private static final int DATE_FROM = 1, DATE_TO = 2;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        Bundle args = this.getArguments();
        mType = args.getInt(TYPE);

        Dialog picker = new android.app.DatePickerDialog(getActivity(), this, year, month, day);
        return picker;

    }
    @Override
    public void onStart() {
        super.onStart();
        // добавляем кастомный текст для кнопки
        Button nButton =  ((AlertDialog) getDialog())
                .getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText(getResources().getString(R.string.ready));

    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {

        TextView textView;
        if (mType == DATE_FROM) {
            textView = (TextView) getActivity().findViewById(R.id.date_edit_from);
            textView.setText(day + "." + month+ "." + year);
        } else if (mType == DATE_TO){
            textView = (TextView) getActivity().findViewById(R.id.date_edit_to);
            textView.setText(day + "." + month+ "." + year);
        }
    }
}