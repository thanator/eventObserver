package com.tan_ds.eventscreator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;
import static com.tan_ds.eventscreator.VeryGlobalVariables.VICTIM;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class ChoosingWhatToDoFragment extends DialogFragment{

    private View mView;
    private RadioButton mRadio1, mRadio2;
    private int mVictim;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        mVictim = args.getInt(VICTIM);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.choose_dialog, null);
        mRadio1 = (RadioButton) mView.findViewById(R.id.delete_nahooi);
        mRadio2 = (RadioButton) mView.findViewById(R.id.izmenit);

        builder.setView(mView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mRadio1.isChecked()){

                        }else if (mRadio2.isChecked()){
                            startActivity(DoSomeThingWithEventActivity
                            .newIntent(getContext())
                            .putExtra(VICTIM, mVictim)
                            .putExtra(TYPE, 1));
                        }
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChoosingWhatToDoFragment.this.getDialog().cancel();
                    }
                });



        return builder.create();
    }
}
