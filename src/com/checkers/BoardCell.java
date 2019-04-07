package com.checkers;



public class BoardCell {


    public enum Content {
        EMPTY, WHITE_PAWN, RED_PAWN
    }

    public enum Color {
        WHITE("#C0C0C0"),
        BLACK("#000000");

        private String colorCode;

        Color(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    private Content content;
    private Color cellColors;

    public BoardCell(Content content, Color cellColors) {
        this.content = content;
        this.cellColors = cellColors;
    }

    public Content getContent() {
        return content;
    }

    public Color getCellColors() {
        return cellColors;
    }
}

