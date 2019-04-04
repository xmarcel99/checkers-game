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

    private Group boardGroup = new Group();
    private Group pawnsGroup = new Group();

    private Parent createLayout() {
        Pane root = new Pane();
        root.setPrefSize(width*partOfBoardSize,height*partOfBoardSize);
        root.getChildren().addAll(boardGroup,pawnsGroup);
        for ( int y = 0; y< height; y ++)
            for (int x = 0; x < width; x++) {

                Board partOfBoard = new Board((y + x) % 2 == 0, x, y);
                boardGroup.getChildren().add(partOfBoard);
                Pawn pawn = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    pawn = makePawn(Pawn.red, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    pawn = makePawn(Pawn.white, x, y);
                }
                if (pawn != null) {
                    pawnsGroup.getChildren().add(pawn);
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

    private Pawn makePawn(String type, int x, int y) {
        Pawn pawn = new Pawn(type, x, y);
        return  pawn;
    }
}