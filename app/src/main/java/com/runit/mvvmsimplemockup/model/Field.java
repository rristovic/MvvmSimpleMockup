package com.runit.mvvmsimplemockup.model;

/*
  Created by Radovan Ristovic on 3/28/2018.
  Quantox.com
  radovanr995@gmail.com
 */

/**
 * Class representing one field in the game.
 * Contains a player that has occupied this field.
 */
public class Field {
    public Player player;

    public Field(Player player) {
        this.player = player;
    }

    /**
     * Checks if this field is empty and ready to be occupied.
     * @return true is this field is free for player to occupy.
     */
    public boolean isEmpty() {
        return player == null;
    }
}
