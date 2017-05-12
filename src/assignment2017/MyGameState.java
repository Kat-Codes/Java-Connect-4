package assignment2017;

import assignment2017.codeprovided.*;

/**
 * 
 * An extended class to provide methods for the game board It also stores the
 * game board, current turn and winner.
 */

public class MyGameState extends Connect4GameState {

    public static int[][] board = new int[NUM_COLS][NUM_ROWS];
    public int[][] copyBoard = new int[NUM_COLS][NUM_ROWS];
    private int winner;
    static int countersInBoard;
    Connect4GameState game;
    private int turn;

    @Override
    public void startGame() {

        game = new MyGameState();
        countersInBoard = 0;
        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                board[col][row] = EMPTY;
            }
        }
    }

    @Override
    public void move(int col) throws ColumnFullException, IllegalColumnException {

        if (col < 0 || col > 6) {
            throw new IllegalColumnException(col);
        } else if (isColumnFull(col)) {
            throw new ColumnFullException(col);
        }
        for (int i = 0; i < NUM_ROWS; i++) {

            if (board[col][i] == EMPTY) {
                board[col][i] = whoseTurn();
                countersInBoard++;
                return;
            }
        }
    }

    public void setBoard(int col, int row, int colour) {
        board[col][row] = colour;
    }

    @Override
    public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {

        if (col < 0 || col > 6) {
            throw new IllegalColumnException(col);
        }

        else if (row < 0 || row > 5) {
            throw new IllegalRowException(row);
        }
        return board[col][row];
    }

    public void setCounterAt(int col, int row, int colour) {
        board[col][row] = colour;
    }

    public boolean verticalWin() {

        int counterColourTotal = 0;

        for (int turn = RED; turn <= YELLOW; turn++) {

            for (int i = 0; i < NUM_COLS; i++) {

                for (int j = 0; j < NUM_ROWS; j++) {

                    if (getCounterAt(i, j) == turn) {
                        counterColourTotal++;
                        if (counterColourTotal == NUM_IN_A_ROW_TO_WIN) {
                            this.winner = turn;
                            return true;
                        }
                    } else {
                        counterColourTotal = 0;
                    }
                }
                // resets counter after starting new column
                counterColourTotal = 0;
            }
        }
        return false;
    }

    public boolean horizontalWin() {

        int counterColourTotal = 0;

        // checks first for red win, then for yellow
        for (int turn = RED; turn <= YELLOW; turn++) {

            for (int j = 0; j < NUM_ROWS; j++) {

                for (int i = 0; i < NUM_COLS; i++) {

                    if (getCounterAt(i, j) == turn) {
                        counterColourTotal++;
                        if (counterColourTotal == NUM_IN_A_ROW_TO_WIN) {
                            this.winner = turn;
                            return true;
                        }
                    } else {
                        counterColourTotal = 0;
                    }
                }
            }
            // resets counter once starting new row
            counterColourTotal = 0;
        }
        return false;
    }

    public boolean forwardsDiagonal() {

        for (int turn = RED; turn <= YELLOW; turn++) {

            for (int row = 0; row < NUM_ROWS - 4; row++) {

                for (int col = 0; col < NUM_COLS - 4; col++) {

                    // checks current counter, upper right
                    if (getCounterAt(col, row) == turn && getCounterAt(col + 1, row + 1) == turn
                            && getCounterAt(col + 2, row + 2) == turn
                            && getCounterAt(col + 3, row + 3) == turn) {
                        this.winner = turn;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean backwardsDiagonal() {

        for (int turn = RED; turn <= YELLOW; turn++) {

            for (int row = NUM_ROWS - 1; row >= 3; row--) {

                for (int col = 0; col < NUM_COLS; col++) {

                    if (getCounterAt(col, row) == turn && getCounterAt(col + 1, row - 1) == turn
                            && getCounterAt(col + 2, row - 2) == turn
                            && getCounterAt(col + 3, row - 3) == turn) {
                        this.winner = turn;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int whoseTurn() {

        if (countersInBoard == 0) {
            return RED;
        } else if (countersInBoard % 2 == 0) {
            return RED;
        } else {
            return YELLOW;
        }
    }

    @Override
    public boolean isBoardFull() {

        for (int i = 0; i < NUM_COLS; i++) {
            // checks if any of the columns are not full
            if (!isColumnFull(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isColumnFull(int col) throws IllegalColumnException {

        if (col < 0 || col > 6) {
            throw new IllegalColumnException(col);
        }

        // checks top counter position of the col being queried
        if (getCounterAt(col, NUM_ROWS - 1) == EMPTY) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getWinner() {

        if (verticalWin() || horizontalWin() || forwardsDiagonal() || backwardsDiagonal()) {
            return this.winner;
        } else {
            return EMPTY;
        }
    }

    @Override
    public boolean gameOver() {

        if (isBoardFull() || getWinner() != EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    public void unplayMove(int col) {

        for (int row = NUM_ROWS - 1; row >= 0; row--) {
            if (getCounterAt(col, row) != EMPTY) {
                setCounterAt(col, row, EMPTY);
                return;
            }
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public Connect4GameState copy() {

        // temporary array
        int[][] boardCopy = new int[NUM_COLS][NUM_ROWS];
        MyGameState copy = new MyGameState();
        for (int col = 0; col < NUM_COLS; col++) {

            for (int row = 0; row < NUM_ROWS; row++) {

                boardCopy[col][row] = board[col][row];
            }
        }
        copy.turn = this.turn;
        copy.board = boardCopy;
        return copy;
    }

    public int[][] copyBoard() {

        // temporary array
        int[][] boardCopy = new int[NUM_COLS][NUM_ROWS];
        // MyGameState copy = new MyGameState();
        for (int col = 0; col < NUM_COLS; col++) {

            for (int row = 0; row < NUM_ROWS; row++) {

                boardCopy[col][row] = board[col][row];
            }
        }
        return boardCopy;
    }
}
