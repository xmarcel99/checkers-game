package com.checkers;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CheckersApp extends Application {
    public static final int partOfBoardSize = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static Board readyBoard = new Board(width, height);
    public static Group boardGroup = new Group();
    private static BorderPane root;
    private Parent createLayout() {
        root = new BorderPane();
        root.setPrefSize(width * partOfBoardSize, height * partOfBoardSize);
        root.getChildren().addAll(boardGroup);
        SaveAndLoadGameProgress.newGame(readyBoard);
        return root;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLayout());
        GameMenu.createMenu();
        BoardDrawer boardDrawer = new BoardDrawer();
        List<BoardElement> boardElements = boardDrawer.draw(readyBoard);
        boardGroup.getChildren().addAll(boardElements);
        MovingPawns.addMovingPawnListener(boardElements, readyBoard);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static  BorderPane getRoot() {
        return root;
    }
}