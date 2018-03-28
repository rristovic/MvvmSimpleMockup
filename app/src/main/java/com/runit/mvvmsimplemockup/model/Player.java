package com.runit.mvvmsimplemockup.model;

/*
  Created by Radovan Ristovic on 3/28/2018.
  Quantox.com
  radovanr995@gmail.com
 */

/**
 * POJO representing a player in the game.
 */
public class Player {
    private String name;
    private String value;

    public Player(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Two players are equal if they have the same value.
     * @param obj object to compare with.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        try {
            Player toCompare = (Player) obj;
            return toCompare.value == this.value;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
