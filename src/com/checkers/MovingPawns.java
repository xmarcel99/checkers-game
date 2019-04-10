package com.checkers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MovingPawns {

    public BoardCell boardCell;
    public BoardDrawer boardDrawer;
    static int oldX, oldY, newX,newY;


    public MovingPawns(BoardDrawer boardDrawer, BoardCell boardCell) {
        this.boardCell = boardCell;
        this.boardDrawer = boardDrawer;
    }

    public static void movingPawn(BoardDrawer boardDrawer,BoardCell boardCell) {
        BoardCell board[][] = CheckersApp.readyBoard.getBoardCells();
        if (boardCell.getContent() == BoardCell.Content.WHITE_PAWN) {

            boardDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    oldX = (int) event.getSceneX() / 100;
                    oldY = (int) event.getSceneY() / 100;
                    //Pawn Move Types, Normal, Kill , None
                    if (board[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN) {
                        board[oldX - 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        board[oldX + 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                    }else if(board[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN
                    && board[oldX + 2][oldY -2].getContent() != BoardCell.Content.RED_PAWN) {
                        board[oldX - 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        board[oldX + 2][oldY - 2] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                    } else if(board[oldX - 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN
                    && board[oldX - 2][oldY -2].getContent() != BoardCell.Content.RED_PAWN) {
                        board[oldX + 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        board[oldX - 2][oldY - 2] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                    } else{
                        //Can't move anywhere so function do nothing here.
                    }
                }
            });
            // MovingPawns pawn
            boardDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    newX = (int) event.getSceneX() / 100;
                    newY = (int) event.getSceneY() / 100;

                    if (board[newX][newY].getContent() == BoardCell.Content.BLUE_PLACE) {
                        board[newX][newY] = new BoardCell(BoardCell.Content.WHITE_PAWN, BoardCell.Color.BLACK);
                        board[oldX][oldY] = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.BLACK);
                    }
                }
            });
        }
    }
}
