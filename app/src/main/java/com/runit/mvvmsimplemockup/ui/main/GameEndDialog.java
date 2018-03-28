package com.runit.mvvmsimplemockup.ui.main;

/**
 * Created by Radovan Ristovic on 3/28/2018.
 * Quantox.com
 * radovanr995@gmail.com
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.runit.mvvmsimplemockup.R;


public class GameEndDialog extends DialogFragment {

    interface OnNewGameCallback {
        void onNewGamePressed();
    }

    private View rootView;
    private String winnerName;
    private OnNewGameCallback mListener;

    public static GameEndDialog newInstance(OnNewGameCallback listener, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.mListener = listener;
        dialog.winnerName = winnerName;
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }


    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }


    private void onNewGame() {
        dismiss();
        mListener.onNewGamePressed();
    }
}