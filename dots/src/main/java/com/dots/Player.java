package com.dots;

import java.util.UUID;

/**
 * Created by melissa on 9/19/16.
 */
public class Player {
    private String gameId;
    private String playerId;
    private String playerType;

    public Player(String newGId) {
        this.gameId = newGId;
        this.playerId = UUID.randomUUID().toString();
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
}
