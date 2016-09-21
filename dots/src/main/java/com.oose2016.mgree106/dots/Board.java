package com.oose2016.mgree106.dots;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melissa on 9/19/16.
 */

public class Board {

    private Line [][] horizontalLines = new Line[5][4];
    private Line [][] verticalLines = new Line[4][5];
    private Box boxes[] = new Box[16];
    private transient int [][] boxArray = new int[4][4];

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

        public void setOwner(String owner) {
            this.owner = owner;
        }
    }

    public Board() {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                horizontalLines[i][j] = new Line(i, j);
                verticalLines[j][i] = new Line(j, i);
                if (i < 4) {
                    boxes[k] = new Box(i, j);
                    boxArray[i][j] = k;
                    k++;
                }
            }
        }

    }

    public void changeBoxOwner(int row, int col, String owner) {
        this.boxes[boxArray[row][col]].setOwner(owner);
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


