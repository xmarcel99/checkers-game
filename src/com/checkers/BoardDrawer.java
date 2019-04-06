package com.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;



import static com.checkers.CheckersApp.partOfBoardSize;

public class BoardDrawer extends StackPane {

    public BoardDrawer(BoardCell boardCell, int x, int y) {

        relocate(x * partOfBoardSize, y * partOfBoardSize);
        Rectangle rectangle = new Rectangle(partOfBoardSize, partOfBoardSize);


        if(boardCell.getContent() == BoardCell.Content.EMPTY && boardCell.getCellColors() == BoardCell.Color.WHITE) {
            rectangle.setFill(Color.valueOf("#C0C0C0"));
            getChildren().addAll(rectangle);

        } else if(boardCell.getContent() == BoardCell.Content.RED_PAWN && boardCell.getCellColors() == BoardCell.Color.BLACK) {
            rectangle.setFill(Color.BLACK);
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);
            bg.setFill(Color.valueOf("#c40003"));
            bg.setStroke(Color.valueOf("#C0C0C0"));
            bg.setStrokeWidth(partOfBoardSize * 0.03);
            bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
            bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);
            getChildren().addAll(rectangle,bg);

        } else if(boardCell.getContent() == BoardCell.Content.WHITE_PAWN && boardCell.getCellColors() == BoardCell.Color.BLACK) {
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);
            rectangle.setFill(Color.BLACK);
            bg.setFill(Color.valueOf("#fff9f4"));
            bg.setStroke(Color.valueOf("#C0C0C0"));
            bg.setStrokeWidth(partOfBoardSize * 0.03);
            bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
            bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);
            getChildren().addAll(rectangle,bg);
        } else if(boardCell.getContent() == BoardCell.Content.EMPTY && boardCell.getCellColors()== BoardCell.Color.BLACK) {
            rectangle.setFill(Color.BLACK);
            getChildren().add(rectangle);
        }

    }
}
