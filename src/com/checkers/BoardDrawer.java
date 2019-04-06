package com.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;



import static com.checkers.CheckersApp.partOfBoardSize;

public class BoardDrawer extends StackPane {

    public BoardDrawer(BoardCell.Color color , BoardCell.Content content, int x, int y) {

        relocate(x * partOfBoardSize, y * partOfBoardSize);
        Rectangle rectangle = new Rectangle(partOfBoardSize, partOfBoardSize);


        if(content == BoardCell.Content.EMPTY && color == BoardCell.Color.WHITE) {
            rectangle.setFill(Color.valueOf("#C0C0C0"));
            getChildren().addAll(rectangle);

        } else if(content == BoardCell.Content.RED_PAWN && color == BoardCell.Color.BLACK) {
            rectangle.setFill(Color.BLACK);
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);
            bg.setFill(Color.valueOf("#c40003"));
            bg.setStroke(Color.valueOf("#C0C0C0"));
            bg.setStrokeWidth(partOfBoardSize * 0.03);
            bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
            bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);
            getChildren().addAll(rectangle,bg);

        } else if(content == BoardCell.Content.WHITE_PAWN && color == BoardCell.Color.BLACK) {
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);
            rectangle.setFill(Color.BLACK);
            bg.setFill(Color.valueOf("#fff9f4"));
            bg.setStroke(Color.valueOf("#C0C0C0"));
            bg.setStrokeWidth(partOfBoardSize * 0.03);
            bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
            bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);
            getChildren().addAll(rectangle,bg);
        } else if(content == BoardCell.Content.EMPTY && color == BoardCell.Color.BLACK) {
            rectangle.setFill(Color.BLACK);
            getChildren().add(rectangle);
        }

    }
}
