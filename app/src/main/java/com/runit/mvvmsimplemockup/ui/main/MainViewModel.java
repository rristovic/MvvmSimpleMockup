package com.runit.mvvmsimplemockup.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.runit.mvvmsimplemockup.model.Game;
import com.runit.mvvmsimplemockup.model.Player;


/**
 * Created by Radovan Ristovic on 3/28/2018.
 * Quantox.com
 * radovanr995@gmail.com
 */

public class MainViewModel extends ViewModel {

    public ObservableArrayMap<String, String> fields = new ObservableArrayMap<>();
    private Game mGame;

    public void init(String player1, String player2) {
        mGame = new Game(player1, player2);
        fields.clear();
    }

    @SuppressLint("DefaultLocale")
    public void onClickedCellAt(int row, int column) {
        if (mGame.populateField(row, column)) {
            fields.put(String.format("%d%d", row, column), mGame.getCurrentPlayer().getValue());
            if (mGame.isGameOver()) {
                mGame.reset();
            } else {
                mGame.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return mGame.getWinner();
    }
}
