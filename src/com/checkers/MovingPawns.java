package com.checkers;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MovingPawns {

    private static int oldX;
    private static int oldY;
    private static  int newX;
    private static int newY;
    private static boolean isSelect = true;
    private static BoardDrawer boardDrawer = new BoardDrawer();

    public static void addMovingPawnListener(List<BoardElement> boardElements, Board board) {
        addListeners(boardElements, board);

    }

    private static void addListeners(List<BoardElement> boardElements, Board board) {
        for (BoardElement element : boardElements) {
            element.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    addMouseClickListener(event, board);
                }
            });
        }
    }

    private static void addMouseClickListener(MouseEvent event, Board board) {
        BoardCell[][] boardCells = board.getBoardCells();

            if (isSelect) {
                oldX = (int) event.getSceneX() / 100;
                oldY = (int) event.getSceneY() / 100;
                if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
                    if (boardCells[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && boardCells[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN) {
                        boardCells[oldX - 1][oldY - 1].setContent(BoardCell.Content.BLUE_PLACE);
                        boardCells[oldX + 1][oldY - 1].setContent(BoardCell.Content.BLUE_PLACE);
                    } else if (boardCells[oldX - 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && boardCells[oldX + 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX + 2][oldY - 2].getContent() != BoardCell.Content.RED_PAWN) {
                        boardCells[oldX - 1][oldY - 1].setContent(BoardCell.Content.BLUE_PLACE);
                        boardCells[oldX + 2][oldY - 2].setContent(BoardCell.Content.BLUE_PLACE);
                    } else if (boardCells[oldX - 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX + 1][oldY - 1].getContent() != BoardCell.Content.RED_PAWN && boardCells[oldX - 2][oldY - 2].getContent() != BoardCell.Content.RED_PAWN) {
                        boardCells[oldX + 1][oldY - 1].setContent(BoardCell.Content.BLUE_PLACE);
                        boardCells[oldX - 2][oldY - 2].setContent(BoardCell.Content.BLUE_PLACE);
                    } else {
                        //Can't move anywhere so function do nothing here.
                    }
                }

                redrawBoard(board);
                isSelect = false;
            } else {

                newX = (int) event.getSceneX() / 100;
                newY = (int) event.getSceneY() / 100;

                if (boardCells[newX][newY].getContent() == BoardCell.Content.BLUE_PLACE) {
                    boardCells[newX][newY].setContent(BoardCell.Content.WHITE_PAWN);
                    boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    for (BoardCell[] x : board.getBoardCells()) {
                        for (BoardCell y : x) {

                            if (y.getContent() == BoardCell.Content.BLUE_PLACE) {
                                y.setContent(BoardCell.Content.EMPTY);
                                y.setCellColors(BoardCell.Color.BLACK);
                            }
                        }
                    }

                    redrawBoard(board);
                }
            }
    }

    private static void redrawBoard(Board board) {
        CheckersApp.boardGroup.getChildren().removeAll();
        List<BoardElement> elementsToDraw = boardDrawer.draw(board);
        addListeners(elementsToDraw, board);
        CheckersApp.boardGroup.getChildren().addAll(elementsToDraw);
    }
}



