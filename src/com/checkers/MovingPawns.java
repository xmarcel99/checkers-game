package com.checkers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MovingPawns {

    public BoardElement boardElement;
    static int oldX, oldY, newX, newY;
    static boolean isSelect = true;



    public MovingPawns(BoardElement boardElement) {
        this.boardElement = boardElement;
    }

    public static void movingPawn(Board board) {

        BoardCell[][] boardCells = board.getBoardCells();
        for (BoardCell[] x : boardCells) {
            for (BoardCell y : x) {
                BoardElement boardElement= new BoardElement(y);

                boardElement.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    Board readyBoard = CheckersApp.readyBoard;
                    BoardCell board[][] = CheckersApp.readyBoard.getBoardCells();
                    @Override
                    public void handle(MouseEvent event) {
                        oldX = (int) event.getSceneX() / 100;
                        oldY = (int) event.getSceneY() / 100;

                        if (board[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN) {
                            board[oldX - 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                            board[oldX + 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        } else if (board[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN
                                && board[oldX + 2][oldY - 2].getContent() != BoardCell.Content.RED_PAWN) {
                            board[oldX - 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                            board[oldX + 2][oldY - 2] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        } else if (board[oldX - 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && board[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN
                                && board[oldX - 2][oldY - 2].getContent() != BoardCell.Content.RED_PAWN) {
                            board[oldX + 1][oldY - 1] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                            board[oldX - 2][oldY - 2] = new BoardCell(BoardCell.Content.BLUE_PLACE);
                        } else {
                            //Can't move anywhere so function do nothing here.
                        }
                        BoardDrawer boardDrawer = new BoardDrawer();
                        CheckersApp.boardGroup.getChildren().removeAll();
                        CheckersApp.boardGroup.getChildren().addAll(boardDrawer.draw(readyBoard));
                        //isSelect = false;
                    }
                });

            }
        }
    }
}

