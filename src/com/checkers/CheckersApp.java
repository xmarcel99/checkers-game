package com.checkers;
import javafx.application.Application;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

                    boardDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            int mouseX = (int )event.getSceneX() / 100;
                            int mouseY = (int) event.getSceneY() / 100;
                            BoardCell boardCell1 = boardCells[mouseX][mouseY];

                            if(boardCell1.getContent() == BoardCell.Content.WHITE_PAWN) {
                                if(boardCells[(mouseX+1)][(mouseY-1)].getContent() != BoardCell.Content.WHITE_PAWN ||
                                        boardCells[(mouseX-1)][(mouseY-1)].getContent() != BoardCell.Content.WHITE_PAWN) {
                                    Rectangle rectangle = new Rectangle((mouseX + 1) * 100, (mouseY - 1) * 100, 100, 100);
                                    Rectangle rectangle1 = new Rectangle((mouseX - 1) * 100, (mouseY - 1) * 100, 100, 100);
                                    rectangle1.setFill(Color.BLUE);
                                    rectangle.setFill(Color.BLUE);
                                    boardGroup.getChildren().addAll(rectangle, rectangle1);
                                }
                            }

                        }
                    });

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

    private BoardDrawer makePartOfBoard(BoardCell boardCell, int x, int y) {
        BoardDrawer partOfBoard = new BoardDrawer(boardCell, x, y);
        return  partOfBoard;
    }
}