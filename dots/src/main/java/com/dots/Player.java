package com.dots;

import java.util.UUID;

/**
 * Created by melissa on 9/19/16.
 */
public class Player {
    private String game;
    protected String playerId;
    protected String playerType;

    public Player(String newGId) {
        this.game = newGId;
        this.playerId = UUID.randomUUID().toString();
    }

}
