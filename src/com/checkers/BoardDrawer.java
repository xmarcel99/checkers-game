package com.checkers;



import java.util.ArrayList;
import java.util.List;

public class BoardDrawer {

    public List<BoardElement> draw(Board board) {

        List<BoardElement> elements = new ArrayList<>();

        for (BoardCell[] x : board.getBoardCells()) {
            for (BoardCell y : x) {

                elements.add(convertToBoardElement(y));
            }
        }

        return elements;
    }

    private BoardElement convertToBoardElement(BoardCell y) {
        BoardElement boardElement = new BoardElement(y);

        return boardElement;
    }

}

