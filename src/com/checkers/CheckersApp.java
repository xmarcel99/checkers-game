package com.checkers;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application {

    public static final int partOfBoardSize = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static Board readyBoard= new Board(width,height);


    private Group boardGroup = new Group();

    private Parent createLayout() {
        BoardDrawer[][] boardCells = readyBoard.getBoardCells();
        Pane root = new Pane();
        root.setPrefSize(width*partOfBoardSize,height*partOfBoardSize);
        root.getChildren().addAll(boardGroup);
        for ( int y = 0; y< height; y ++)
            for (int x = 0; x < width; x++) {

                BoardDrawer boardDrawer = null;
                if((x + y) % 2 == 0) {
                    boardDrawer = makePartOfBoard(BoardCell.Color.WHITE, BoardCell.Content.EMPTY,x,y);
                } else {
                    if (y <= 2 ) {
                        boardDrawer = makePartOfBoard(BoardCell.Color.BLACK, BoardCell.Content.RED_PAWN,x,y);
                    }
                    if(y >= 5) {
                        boardDrawer = makePartOfBoard(BoardCell.Color.BLACK, BoardCell.Content.WHITE_PAWN,x,y);
                    }
                    if(y > 2 && y < 5) {
                        boardDrawer = makePartOfBoard(BoardCell.Color.BLACK, BoardCell.Content.EMPTY,x,y);
                    }
                }
                if(boardDrawer != null) {
                    boardGroup.getChildren().add(boardDrawer);
                }
                boardCells[x][y] = boardDrawer;

            }
        return root;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createLayout());

        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private BoardDrawer makePartOfBoard(BoardCell.Color color , BoardCell.Content content, int x, int y) {
        BoardDrawer partOfBoard = new BoardDrawer(color,content, x, y);
        return  partOfBoard;
    }
}