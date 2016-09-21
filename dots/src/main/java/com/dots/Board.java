package com.dots;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melissa on 9/19/16.
 */

public class Board {

    private Line [][] horizontalLines = new Line[5][4];
    private Line [][] verticalLines = new Line[4][5];
    private Map<Coordinate, Box> boxes;

    private class Coordinate {
        private final int row;
        private final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private class Line {
        private final int row;
        private final int col;
        private boolean filled;


        public Line (int r, int c) {
            this.row = r;
            this.col = c;
            this.filled = false;
        }

        public void toggleFilled() {
            this.filled = true;
        }

        public boolean isFilled() {
            return this.filled;
        }

    }

    private class Box {
        private final int row;
        private final int col;
        private String owner;

        public Box(int r, int c) {
            this.row = r;
            this.col = c;
            this.owner = "NONE";
        }
    }

    public Board() {
        boxes = new HashMap<Coordinate, Box>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                horizontalLines[i][j] = new Line(i, j);
                verticalLines[j][i] = new Line(j, i);
                if (i < 4) {
                    boxes.put(new Coordinate(i, j), new Box(i, j));
                }
            }
        }

    }

    public boolean isHFilled(int r, int c){
        return this.horizontalLines[r][c].isFilled();
    }

    public boolean isVFilled(int r, int c){
        return this.verticalLines[r][c].isFilled();
    }

    public void toggleVFilled(int r, int c) {
        this.verticalLines[r][c].toggleFilled();
    }

    public void toggleHFilled(int r, int c) {
        this.horizontalLines[r][c].toggleFilled();
    }

    public Line[][] getHorizontalLines() {
        return horizontalLines;
    }

    public Line[][] getVerticalLines() {
        return verticalLines;
    }
}
