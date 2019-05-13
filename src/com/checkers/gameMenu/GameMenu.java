package com.checkers.gameMenu;

import com.checkers.CheckersApp;
import com.checkers.gameProgress.SaveAndLoadGameProgress;
import com.checkers.movement.MovingPawns;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class GameMenu  {

    public static void createMenu() {
        BorderPane root = CheckersApp.getRoot();
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("Menu");
        MenuItem newGameMenuItem = new MenuItem("New Game");
        MenuItem loadMenuItem = new MenuItem("Load");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        loadMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SaveAndLoadGameProgress.loadGame(CheckersApp.readyBoard);
                MovingPawns.redrawBoard(CheckersApp.readyBoard);
            }
        });
        newGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SaveAndLoadGameProgress.newGame(CheckersApp.readyBoard);
                MovingPawns.redrawBoard(CheckersApp.readyBoard);
            }
        });
        fileMenu.getItems().addAll(newGameMenuItem, loadMenuItem, new SeparatorMenuItem(), exitMenuItem);
        menuBar.getMenus().addAll(fileMenu);
    }
}