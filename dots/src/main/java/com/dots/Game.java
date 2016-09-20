package com.dots;


/**
 * Created by melissa on 9/19/16.
 */
public class Game {
    private String gameId;
    private Player host;
    private Player guest;
    private GameState current_state;
    protected Board current_board;

    private class GameState {
        protected String state;
        protected int redScore;
        protected int blueScore;
        protected String whoseTurn;

        public GameState() {
            this.state = "WAITING_TO_START";
            this.redScore = 0;
            this.blueScore = 0;
        }
    }

    public Game(String newGameId){
        this.host = new Player(newGameId);
        this.guest = new Player(newGameId);
        this.gameId = newGameId;
        this.current_board = new Board();
    }

    public Board getCurrent_board() {
        return this.current_board;
    }

    public GameState getCurrent_state() {
        return this.current_state;
    }

    public Player getHost() {
        return this.host;
    }

    public Player getGuest() {
        return this.guest;
    }

    public void setStatus(String newStatus) {
        this.current_state.state = newStatus; //error checking
    }

    public void setHostType(String pType) {
        host.playerType = pType;
        current_state.whoseTurn = pType;
        if (pType.compareTo("RED") == 0) {
            guest.playerType = "BLUE";
        } else {
            guest.playerType = "RED";
        }
    }
}
