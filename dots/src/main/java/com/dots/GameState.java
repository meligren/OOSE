package com.dots;

/**
 * Created by melissa on 9/20/16.
 */
public class GameState {
    private String state;
    private int redScore;
    private int blueScore;
    private String whoseTurn;

    public GameState() {
        this.state = "WAITING_TO_START";
        this.redScore = 0;
        this.blueScore = 0;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public int getRedScore() {
        return redScore;
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public String getState() {
        return state;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public void setWhoseTurn(String whoseTurn) {
        this.whoseTurn = whoseTurn;
    }
}
