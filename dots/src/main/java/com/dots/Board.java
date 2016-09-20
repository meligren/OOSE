package com.dots;

import java.util.List;

/**
 * Created by melissa on 9/19/16.
 */

public class Board {

    protected List<Line> horizontalLines;
    protected List<Line> verticalLines;

    private class Line {
        protected final int row;
        protected final int col;
        protected boolean filled;

        public Line (int r, int c) {
            this.row = r;
            this.col = c;
            this.filled = false;
        }
    }
    public Board() {
        for (int i = 1; i < 5; i++ ) {
            for (int j = 1; j < 5; j++) {
                this.horizontalLines.add(new Line(i, j));
                this.verticalLines.add(new Line(i, j));
            }
        }
    }

}
