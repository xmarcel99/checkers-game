package com.checkers;

public class Board {

    private BoardCell[][] boardCells;

    public Board(int width, int heigth) {
        this.boardCells = new BoardCell[width][heigth];
    }

    public BoardCell[][] getBoardCells() {
        return boardCells;
    }
}
