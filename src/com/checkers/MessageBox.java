package com.checkers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    public static void displayBox() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Thanks for playing");
        window.setMinWidth(250);
        Label label = new Label();
        if(Board.areRedWon){
            label.setText("Red's won the game ! ");
        }else {
            label.setText("You won the game ! ");
        }
        Button newGamebutton = new Button("New Game");

        newGamebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SaveAndLoadGameProgress.newGame(CheckersApp.readyBoard);
                MovingPawns.redrawBoard(CheckersApp.readyBoard);
                window.close();
            }
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,newGamebutton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
