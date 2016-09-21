package com.oose2016.mgree106.dots;
/**
 * Created by melissa on 9/19/16.
 */
public class Game {
    private Player host;
    private Player guest;
    private GameState current_state;
    private Board current_board;
    private transient Player turn;
    private transient int totalMoves;

    public Game(String newGameId){
        this.host = new Player(newGameId);
        this.guest = new Player(newGameId);
        this.current_state = new GameState();
        this.current_board = new Board();
        this.totalMoves = 0;
    }

    public void setStatus(String newStatus) {
        this.current_state.setState(newStatus);
    }

    public String getStatus() {
        return this.current_state.getState();
    }

    public Board getCurrent_board() {
        return this.current_board;
    }

    public GameState getCurrent_state(){
        return this.current_state;
    }

    public Player getHost() {
        return this.host;
    }

    public Player getGuest() {
        return this.guest;
    }

    /** Set host to blue or red */
    public void setHostType(String pType) {
        pType = pType.substring(15, pType.length() - 2);
        host.setPlayerType(pType);
        if (pType.toUpperCase().compareTo("RED") == 0) {
            guest.setPlayerType("BLUE");
            this.turn = this.host;
        } else {
            guest.setPlayerType("RED");
            this.turn = this.guest;
        }
        current_state.setWhoseTurn("RED");

    }

    /** Change turn */
    public void toggleTurn() {
        if (this.turn == this.host) {
            this.turn = this.guest;
            this.current_state.setWhoseTurn(this.guest.getPlayerType());
        } else {
            this.turn = this.host;
            this.current_state.setWhoseTurn(this.host.getPlayerType());
        }
    }

    /** Change the vertical lines. */
    public boolean updateVBoard(int row, int col, String id) {
        if (this.current_board.isVFilled(row, col)) {
            return false;
        }else if (this.turn.getPlayerId().compareTo(id) != 0){
            return false;
        }else {
            this.current_board.toggleVFilled(row, col);
            this.checkMoves();

            //check if box is made, if box is not made, then switch players
            if(boxMade(row, col, 'v') == 1) {
                //same col, row box
                this.current_board.changeBoxOwner(row, col, this.turn.getPlayerType());
                this.updateScore(this.turn.getPlayerType());
            } else if (boxMade(row, col, 'v') == 0) {
                //same row, col - 1 box
                this.current_board.changeBoxOwner(row, col - 1, this.turn.getPlayerType());
                this.updateScore(this.turn.getPlayerType());

            } else {
                this.toggleTurn();
            }
            return true;
        }
    }

    /** Change horizontal lines */
    public boolean updateHBoard(int row, int col, String id) {
        if (this.current_board.isHFilled(row, col)) {
            return false;
        }else if (this.turn.getPlayerId().compareTo(id) != 0){
            return false;
        } else {
            this.current_board.toggleHFilled(row, col);
            this.checkMoves();

            //check if box is made, if box is not made, then switch players
            if(boxMade(row, col, 'h') == 1) {
                //same col, row - 1 box
                this.current_board.changeBoxOwner(row, col, this.turn.getPlayerType());
                this.updateScore(this.turn.getPlayerType());
            } else if (boxMade(row, col, 'h') == 0) {
                //same col and row box
                this.current_board.changeBoxOwner(row - 1, col, this.turn.getPlayerType());
                this.updateScore(this.turn.getPlayerType());
            } else {
                this.toggleTurn();
            }
            return true;
        }
    }

    /** Check whether or not game is over. */
    private void checkMoves(){
        if(this.totalMoves == 40) {
            this.current_state.setState("FINISHED");
        } else {
            this.totalMoves++;
        }
    }

    /** Change player's score. */
    private void updateScore(String player) {
        if (player.compareTo("RED") == 0) {
            this.current_state.increaseRedScore();
        } else {
            this.current_state.increaseBlueScore();
        }
    }

    private int boxMade(int row, int col, char moveType) {
        //right box is 1, left box is 0, no box made is -1
        if (moveType == 'v') {
            // check if made box to left or right
            if (col < 4 && col > 0) {
                if (this.current_board.isHFilled(row, col) && this.current_board.isVFilled(row, col + 1) && this.current_board.isHFilled(row + 1, col)) {
                    return 1;
                } else if (this.current_board.isHFilled(row, col - 1) && this.current_board.isVFilled(row, col - 1) && this.current_board.isHFilled(row + 1, col - 1)) {
                    return 0;
                }
            } else if (col == 4) {
                //can only make a box to the left
                if (this.current_board.isHFilled(row, col - 1) && this.current_board.isVFilled(row, col - 1) && this.current_board.isHFilled(row + 1, col - 1)) {
                    return 0;
                }
            } else if (col == 0) {
                // can only make a box to the right
                if (this.current_board.isHFilled(row, col) && this.current_board.isVFilled(row, col + 1) && this.current_board.isHFilled(row + 1, col)) {
                    return 1;
                }
            }
        } else if (moveType == 'h') {
            //0 is up, 1 is down, -1 is no box
            if (row < 4 && row > 0) {
                if (this.current_board.isVFilled(row, col) && this.current_board.isVFilled(row, col + 1) && this.current_board.isHFilled(row + 1, col)) {
                    return 1;
                } else if (this.current_board.isHFilled(row - 1, col) && this.current_board.isVFilled(row - 1, col) && this.current_board.isHFilled(row - 1, col + 1)) {
                    return 0;
                }
            } else if (row == 4) {
                //cna only make box above
                if (this.current_board.isHFilled(row - 1, col) && this.current_board.isVFilled(row - 1, col) && this.current_board.isHFilled(row - 1, col + 1)) {
                    return 0;
                }
            } else if (row == 0) {
                //can only make box below
                if (this.current_board.isVFilled(row, col) && this.current_board.isVFilled(row, col + 1) && this.current_board.isHFilled(row + 1, col)) {
                    return 1;
                }
            }
        } else {
            //error
        }
        return -1;
    }

}
