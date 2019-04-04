package com.checkers;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Group {

    public Board(boolean light, int x, int y) {

        relocate(x * CheckersApp.partOfBoardSize, y * CheckersApp.partOfBoardSize);
        Rectangle rectangle = new Rectangle(CheckersApp.partOfBoardSize, CheckersApp.partOfBoardSize);

        if(light) {
            rectangle.setFill(Color.valueOf("#C0C0C0"));
        } else {
            rectangle.setFill(Color.valueOf("#000000"));
        }
        getChildren().add(rectangle);
    }
}
