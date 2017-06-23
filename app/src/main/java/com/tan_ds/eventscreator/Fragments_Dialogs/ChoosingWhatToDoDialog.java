package com.tan_ds.eventscreator.Fragments_Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.tan_ds.eventscreator.Activities.DoSomeThingWithEventActivity;
import com.tan_ds.eventscreator.Activities.EventActivity;
import com.tan_ds.eventscreator.R;

import static com.tan_ds.eventscreator.VeryGlobalVariables.TYPE;
import static com.tan_ds.eventscreator.VeryGlobalVariables.VICTIM;

/**
 * Created by Tan-DS on 6/14/2017.
 */

public class ChoosingWhatToDoDialog extends DialogFragment{

    private View mView;
    private RadioButton mRadio1, mRadio2;
    private int mVictim;
    private final static int UPDATE = 1;
    private onCompleteListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            this.mListener = (onCompleteListener) context;
        } catch (final ClassCastException e){
            throw new ClassCastException(context.toString() + "ERROR");
        }
    }

    private void onDeleteItem(int position){
        this.mListener.onComplete(position);
    }

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
                            onDeleteItem(mVictim);
                        }else if (mRadio2.isChecked()){
                            startActivityForResult(DoSomeThingWithEventActivity
                            .newIntent(getContext())
                            .putExtra(VICTIM, mVictim)
                            .putExtra(TYPE, 1), UPDATE);
                        }
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChoosingWhatToDoDialog.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }

    public static interface onCompleteListener{
        public abstract void onComplete(int position);
    }
}
