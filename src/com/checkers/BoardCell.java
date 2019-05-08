package com.checkers;

import java.io.Serializable;

public class BoardCell implements Serializable {

    private static final long serialVersionUID = 1L;
    public enum Content {
        EMPTY(999), WHITE_PAWN(1), RED_PAWN(-1), BLUE_PLACE(0), WHITE_KING(1),RED_KING(-1);
        int content;
        Content(int content) {
            this.content = content;
        }
        public   int getContentInInt() {return content;}
        public Content getOppositeContent () {return new BoardCell(-getContentInInt()).getContent();}
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

    public BoardCell(int content) {
        if(content == 1) {
            setContent(Content.WHITE_PAWN);
        } else {
            setContent(Content.RED_PAWN);
        }
    }

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


