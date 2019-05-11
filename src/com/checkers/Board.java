package com.checkers;

import java.io.Serializable;

public class Board implements Serializable {
    private static BoardCell[][] boardCells;
    private static final long serialVersionUID = 3L;
    static boolean areRedWon;

    public Board(int width, int heigth) {
        this.boardCells = new BoardCell[width][heigth];
    }

    public BoardCell[][] getBoardCells() {
        return boardCells;
    }

    public static void setBoardCells(BoardCell[][] boardCells) {
        Board.boardCells = boardCells;
    }

    public static boolean isEndOfGame(Board board) {
        int counterOfWhitePawns = 0;
        int counterOfRedPawn = 0;
        for (BoardCell[] x : board.getBoardCells()) {
            for (BoardCell y : x) {
                if (y.getContent().getContentInInt() == 1) {
                    counterOfWhitePawns++;
                } else if (y.getContent().getContentInInt() == -1) {
                    counterOfRedPawn++;
                }
            }
        }
        if (counterOfRedPawn == 0 && counterOfWhitePawns > 0) {
            areRedWon = false;
            MessageBox.displayBox();
            return true;
        } else if (counterOfWhitePawns == 0 && counterOfRedPawn > 0) {
            areRedWon = true;
            MessageBox.displayBox();
            return true;
        } else {
            return false;
        }
    }

}
