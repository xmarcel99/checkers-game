package com.checkers;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.checkers.MovingPawns.redrawBoard;

public class CheckersApp extends Application {
    public static final int partOfBoardSize = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static Board readyBoard = new Board(width, height);
    public static Group boardGroup = new Group();
    public static  boolean isEndOfGame = true;

    private Parent createLayout() {
        Pane root = new Pane();
        root.setPrefSize(width * partOfBoardSize, height * partOfBoardSize);
        root.getChildren().addAll(boardGroup);
        File file = new File("1.save");
        File file1 = new File("2.save");
        File file2 = new File("3.save");
        try {
             isEndOfGame = (boolean) SaveAndLoadGameProgress.loadGameProgress("3.save");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (file.exists() && file.isFile() && !isEndOfGame) {
            try {
                BoardCell[][] boardCells = (BoardCell[][]) SaveAndLoadGameProgress.loadGameProgress("1.save");
                readyBoard.setBoardCells(boardCells);
                MovingPawns.removeAllBluePlacesForBoard(readyBoard);
                redrawBoard(readyBoard);
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {

                        BoardCell boardCell = null;
                        if ((x + y) % 2 == 0) {
                            boardCell = readyBoard.getBoardCells()[x][y];

                        } else {
                            if (y <= 2) {
                                boardCell = readyBoard.getBoardCells()[x][y];
                            }
                            if (y >= 5) {
                                boardCell = readyBoard.getBoardCells()[x][y];
                            }
                            if (y > 2 && y < 5) {
                                boardCell = readyBoard.getBoardCells()[x][y];
                            }
                        }
                        boardCells[x][y] = boardCell;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (file1.exists() && file1.isFile()) {
                try {
                    MovingPawns.whitePawnTurn = ((boolean) SaveAndLoadGameProgress.loadGameProgress("2.save"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if(isEndOfGame){
            MovingPawns.whitePawnTurn = true;
            BoardCell[][] boardCells = readyBoard.getBoardCells();

            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++) {

                    BoardCell boardCell = null;
                    if ((x + y) % 2 == 0) {
                        boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.WHITE, x, y);

                    } else {
                        if (y <= 2) {
                            boardCell = new BoardCell(BoardCell.Content.RED_PAWN, BoardCell.Color.BLACK, x, y);
                        }
                        if (y >= 5) {
                            boardCell = new BoardCell(BoardCell.Content.WHITE_PAWN, BoardCell.Color.BLACK, x, y);
                        }
                        if (y > 2 && y < 5) {
                            boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.BLACK, x, y);
                        }
                    }
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
        BoardDrawer boardDrawer = new BoardDrawer();
        List<BoardElement> boardElements = boardDrawer.draw(readyBoard);
        boardGroup.getChildren().addAll(boardElements);
        MovingPawns.addMovingPawnListener(boardElements, readyBoard);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}