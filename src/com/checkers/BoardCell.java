package com.checkers;

public class BoardCell  {


    public enum Content {
        EMPTY, WHITE_PAWN, RED_PAWN, BLUE_PLACE;
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
    private int x;
    private  int y;

    public BoardCell(Content content, Color cellColors,int x, int y) {
        this.x = x;
        this.y = y;
        this.content = content;
        this.cellColors = cellColors;
    }

    public Content getContent() {
        return content;
    }

    public Color getCellColors() {
        return cellColors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setCellColors(Color cellColors) {
        this.cellColors = cellColors;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}


