package com.checkers;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


import static com.checkers.CheckersApp.partOfBoardSize;

public class Pawn extends StackPane {
    static String red = "Red";
    static  String white = "White";

    public Pawn(String type, int x , int y) {

        relocate(x * partOfBoardSize, y * partOfBoardSize);
        Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);

        if(type.equals("Red")) {
            bg.setFill(Color.valueOf("#c40003"));
        } else {
            bg.setFill(Color.valueOf("#fff9f4"));
        }

        bg.setStroke(Color.valueOf("#C0C0C0"));
        bg.setStrokeWidth(partOfBoardSize * 0.03);
        bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
        bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);

       getChildren().addAll(bg);

    }
}
