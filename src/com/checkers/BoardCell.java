package com.checkers;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import static com.checkers.CheckersApp.partOfBoardSize;


public class BoardCell extends Group {

        public BoardCell(boolean emptyCell,int x , int y) {

            relocate(x * CheckersApp.partOfBoardSize, y * CheckersApp.partOfBoardSize);
            Rectangle rectangle = new Rectangle(CheckersApp.partOfBoardSize,CheckersApp.partOfBoardSize);
            Ellipse bg = new Ellipse(partOfBoardSize * 0.3125, partOfBoardSize * 0.26);

            if(emptyCell) {
                rectangle.setFill(Color.valueOf("#C0C0C0"));
                getChildren().add(rectangle);
            } else {
                rectangle.setFill(Color.valueOf("#000000"));

                if (y <= 2 && (x + y) % 2 != 0) {
                    bg.setFill(Color.valueOf("#c40003"));
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    bg.setFill(Color.valueOf("#fff9f4"));
                }
                bg.setStroke(Color.valueOf("#C0C0C0"));
                bg.setStrokeWidth(partOfBoardSize * 0.03);
                bg.setTranslateX((partOfBoardSize - partOfBoardSize * 0.3125 * 2) / 2);
                bg.setTranslateY((partOfBoardSize - partOfBoardSize * 0.26 * 2) / 2);
                getChildren().addAll(rectangle,bg);
            }
        }
}
