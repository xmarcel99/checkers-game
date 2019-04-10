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

        BoardCell[][] boardCells = readyBoard.getBoardCells();
        Pane root = new Pane();
        root.setPrefSize(width*partOfBoardSize,height*partOfBoardSize);
        root.getChildren().addAll(boardGroup);
        for ( int y = 0; y< height; y ++)
            for (int x = 0; x < width; x++) {



                BoardDrawer boardDrawer = null;
                BoardCell boardCell = null;
                if ((x + y) % 2 == 0) {
                    boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.WHITE);
                    boardDrawer = makePartOfBoard(boardCell, x, y);
                } else {
                    if (y <= 2) {
                        boardCell = new BoardCell(BoardCell.Content.RED_PAWN, BoardCell.Color.BLACK);
                        boardDrawer = makePartOfBoard(boardCell, x, y);
                    }
                    if (y >= 5) {
                        boardCell = new BoardCell(BoardCell.Content.WHITE_PAWN, BoardCell.Color.BLACK);
                        boardDrawer = makePartOfBoard(boardCell, x, y);
                    }
                    if (y > 2 && y < 5) {
                        boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.BLACK);
                        boardDrawer = makePartOfBoard(boardCell, x, y);
                    }
                }


                if (boardDrawer != null) {
                    boardGroup.getChildren().add(boardDrawer);
                    boardCells[x][y] = boardCell;
                }


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

    public  static BoardDrawer makePartOfBoard(BoardCell boardCell, int x, int y) {
        BoardDrawer partOfBoard = new BoardDrawer(boardCell, x, y);
        return  partOfBoard;
    }
}