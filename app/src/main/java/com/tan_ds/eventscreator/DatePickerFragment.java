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
import java.util.HashMap;
import java.util.Map;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private int mType;

    private static final int DATE_FROM = 1, DATE_TO = 2;

    private static Map<Integer, String> monthMap = new HashMap<Integer, String>(){{
        put(0, "Январь");
        put(1, "Февраль");
        put(2, "Март");
        put(3, "Апрель");
        put(4, "Май");
        put(5, "Июнь");
        put(6, "Июль");
        put(7, "Август");
        put(8, "Сентябрь");
        put(9, "Октябрь");
        put(10, "Ноябрь");
        put(11, "Декабрь");
    }};

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

        TextView yearView, monthView, dayView;
        if (mType == DATE_FROM) {
            yearView = (TextView) getActivity().findViewById(R.id.year_from);
            monthView = (TextView) getActivity().findViewById(R.id.month_from);
            dayView = (TextView) getActivity().findViewById(R.id.day_from);

            yearView.setText(""+year);
            monthView.setText(""+monthMap.get(month));
            dayView.setText(""+day);
        } else if (mType == DATE_TO){
            yearView = (TextView) getActivity().findViewById(R.id.year_to);
            monthView = (TextView) getActivity().findViewById(R.id.month_to);
            dayView = (TextView) getActivity().findViewById(R.id.day_to);

            yearView.setText(""+year);
            monthView.setText(""+monthMap.get(month));
            dayView.setText(""+day);
        }
    }
}