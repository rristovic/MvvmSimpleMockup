package com.runit.mvvmsimplemockup.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

/*
  Created by Radovan Ristovic on 3/28/2018.
  Quantox.com
  radovanr995@gmail.com
 */

/**
 * Game class which contains business logic. Requires two players for the game to be played.
 * Exposes winner through Android Arch Component {@link android.arch.lifecycle.LiveData}.
 */
public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    private Player player1;
    private Player player2;

    // Hold the current player that is choosing his field.
    private Player currentPlayer = player1;

    // Game playable fields.
    private Field[][] fields;

    // Holds the winner of the game.
    private MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String firstPlayer, String secondPlayer) {
        fields = new Field[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(firstPlayer, "x");
        player2 = new Player(secondPlayer, "o");
        currentPlayer = player1;
    }

    /**
     * Method for checking is the game has ended.
     *
     * @return true if the game has no more fields available for play.
     */
    public boolean isGameOver() {
        if (isHorizontalLineComplete() || isVerticalLineComplete() || isDiagonalLineComplete()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner = null;
            return true;
        }

        return false;
    }

    /**
     * Method for populating a field with the current player.
     *
     * @param row    field row number in the game.
     * @param column field column number in the game.
     * @return false if field has already been populated.
     */
    public boolean populateField(int row, int column) {
        if (fields == null || row >= fields.length || column >= fields[row].length) {
            return false;
        }

        if (fields[row][column] == null) {
            fields[row][column] = new Field(currentPlayer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method for checking is any of vertical line od fields has been populated by the same player.
     *
     * @return true if one player has populated all fields in a line.
     */
    private boolean isVerticalLineComplete() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(fields[0][i], fields[1][i], fields[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * Helper method for checking is any of horizontal line od fields has been populated by the same player.
     *
     * @return true if one player has populated all fields in a line.
     */
    private boolean isHorizontalLineComplete() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(fields[i][0], fields[i][1], fields[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * Helper method for checking is any of diagonal line od fields has been populated by the same player.
     *
     * @return true if one player has populated all fields in a line.
     */
    private boolean isDiagonalLineComplete() {
        try {
            return areEqual(fields[0][0], fields[1][1], fields[2][2]) ||
                    areEqual(fields[0][2], fields[1][1], fields[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * Helper method for checking is player board is full.
     *
     * @return true if board is full.
     */
    private boolean isBoardFull() {
        for (Field[] row : fields)
            for (Field cell : row)
                if (cell == null || cell.isEmpty())
                    return false;
        return true;
    }

    /**
     * Method for checking if fields are populated by the same player.
     *
     * @param cells Fields to check if are equal.
     * @return true if all fields are the same.
     */
    private boolean areEqual(Field... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Field cell : cells)
            if (cell == null)
                return false;

        Field f = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!f.player.equals(cells[i].player))
                return false;

        return true;
    }

    /**
     * Switch to another play in the game.
     */
    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    /**
     * Reset to game to its initial state.
     */
    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        fields = null;
    }

    /**
     * Return the game winner. If there is no winner, value is set to null.
     *
     * @return {@link Player} object who won the game or null if there is no winner.
     */
    public LiveData<Player> getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}