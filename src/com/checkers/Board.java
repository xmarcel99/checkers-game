package com.checkers;

public class Board  {

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
