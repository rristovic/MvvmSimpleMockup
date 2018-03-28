package com.runit.mvvmsimplemockup.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.runit.mvvmsimplemockup.R;
import com.runit.mvvmsimplemockup.databinding.ActivityMainBinding;
import com.runit.mvvmsimplemockup.model.Player;

/**
 * Created by Radovan Ristovic on 3/28/2018.
 * Quantox.com
 * radovanr995@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private static final String NO_WINNER = "No one";
    private MainViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this::initDataBinding);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityMainBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        gameViewModel.init(player1, player2);
        activityGameBinding.setMainViewModel(gameViewModel);
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    private void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null ? NO_WINNER : winner.getName();
        GameEndDialog dialog = GameEndDialog.newInstance(this::promptForPlayers, winnerName);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }
}
