package com.checkers;

public class Board {

    private BoardDrawer[][] boardCells;

    public Board(int width, int heigth) {
        this.boardCells = new BoardDrawer[width][heigth];
    }

    public BoardDrawer[][] getBoardCells() {
        return boardCells;
    }
}
