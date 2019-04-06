package com.checkers;

public class BoardCell {

    public enum Content {
        EMPTY, WHITE_PAWN, RED_PAWN
    }

    public enum Color {
        WHITE, BLACK
    }

    private Content content;
    private Color cellColors;
}
