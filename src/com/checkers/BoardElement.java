package com.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import static com.checkers.CheckersApp.partOfBoardSize;

public class BoardElement extends StackPane  {


    public BoardElement(BoardCell boardCell) {
        int x = boardCell.getX();
        int y = boardCell.getY();

        if (boardCell.getContent() == BoardCell.Content.EMPTY) {
            relocate(x * partOfBoardSize, y * partOfBoardSize);
            Rectangle rectangle = new Rectangle(partOfBoardSize, partOfBoardSize);
            rectangle.setFill(Color.valueOf(boardCell.getCellColors().getColorCode()));
            getChildren().addAll(rectangle);
        } else {
            relocate(x * partOfBoardSize, y * partOfBoardSize);
            Rectangle rectangle = new Rectangle(partOfBoardSize, partOfBoardSize);
            rectangle.setFill(Color.valueOf(boardCell.getCellColors().getColorCode()));
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);
            if (boardCell.getContent() == BoardCell.Content.RED_PAWN) {
                bg.setFill(Color.valueOf("#c40003"));
                bg.setStroke(Color.valueOf("#C0C0C0"));
                bg.setStrokeWidth(partOfBoardSize * 0.03);
            } else if (boardCell.getContent() == BoardCell.Content.WHITE_PAWN) {
                bg.setFill(Color.valueOf("#fff9f4"));
                bg.setStroke(Color.valueOf("#C0C0C0"));
                bg.setStrokeWidth(partOfBoardSize * 0.03);
            }else if (boardCell.getContent() == BoardCell.Content.WHITE_KING) {
                bg.setFill(Color.valueOf("#00FF00"));
                bg.setStroke(Color.WHITE);
                bg.setStrokeWidth(partOfBoardSize *  0.23);
            } else if (boardCell.getContent() == BoardCell.Content.RED_KING) {
                bg.setFill(Color.valueOf("#00FF00"));
                bg.setStroke(Color.RED);
                bg.setStrokeWidth(partOfBoardSize *  0.23);
            }
            bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.48 * 2) / 2);
            bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.49 * 2) / 2);
            getChildren().addAll(rectangle, bg);

        }

        if(boardCell.getContent() == BoardCell.Content.BLUE_PLACE) {
            Rectangle rectangle = new Rectangle((x)*100,(y)*100,partOfBoardSize, partOfBoardSize);
            rectangle.setFill(Color.BLUE);
            getChildren().add(rectangle);
        }

    }
}