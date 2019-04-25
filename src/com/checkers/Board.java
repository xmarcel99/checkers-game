package com.checkers;

import java.io.Serializable;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    private static  BoardCell[][] boardCells;

    public Board(int width, int heigth) {
        this.boardCells = new BoardCell[width][heigth];
    }

    public BoardCell[][] getBoardCells() {
        return boardCells;
    }

    public static void setBoardCells(BoardCell[][] boardCells) {
        Board.boardCells = boardCells;
    }
}
