package com.checkers.board;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDrawer {
    public List<BoardElement> draw(Board board) {
        List<BoardElement> elements = Arrays.stream(board.getBoardCells()).flatMap(Arrays::stream).map(BoardElement::new).collect(Collectors.toList());
        return elements;
    }
}

