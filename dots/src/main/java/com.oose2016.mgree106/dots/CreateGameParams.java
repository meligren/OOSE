package com.oose2016.mgree106.dots;

/**
 * Created by melissa on 9/20/16.
 */
public class CreateGameParams{
    private String playerId;
    private int row;
    private int col;

    public String getPlayerId(){
        return this.playerId;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
