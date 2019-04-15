package com.checkers;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MovingPawns {

    private static int oldX;
    private static int oldY;
    private static int newX;
    private static int newY;
    private static boolean isSelect = true;
    private static BoardDrawer boardDrawer = new BoardDrawer();
    static boolean  whitePawnTurn = true;

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
            if (whitePawnTurn) {
                movePawn(board,oldX,oldY,1);
            }
            else {
                movePawn(board,oldX,oldY,-1);
        }
        } else {

            newX = (int) event.getSceneX() / 100;
            newY = (int) event.getSceneY() / 100;

            if (boardCells[newX][newY].getContent() == BoardCell.Content.BLUE_PLACE) {

                if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {

                    if (oldX == 0) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                        if(boardCells[oldX +1][oldY -1].getContent() == BoardCell.Content.RED_PAWN ) {
                            boardCells[oldX +1][oldY -1].setContent(BoardCell.Content.EMPTY);
                        }
                    }else if ( oldX == 7) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    }else if (oldX == 1) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    }else if (boardCells[oldX - 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX - 2][oldY - 2].getContent() == BoardCell.Content.BLUE_PLACE) {
                        boardCells[oldX - 1][oldY - 1].setContent(BoardCell.Content.EMPTY);
                    }else if (oldX + 1 == 7) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    } else if (boardCells[oldX + 1][oldY - 1].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX + 2][oldY - 2].getContent() == BoardCell.Content.BLUE_PLACE) {
                        boardCells[oldX + 1][oldY - 1].setContent(BoardCell.Content.EMPTY);
                    }
                    boardCells[newX][newY].setContent(BoardCell.Content.WHITE_PAWN);
                    boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);

                } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN) {
                    if (oldX == 0) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    } else if( oldX == 6 && boardCells[oldX -1][oldY +1].getContent() == BoardCell.Content.WHITE_PAWN) {
                        boardCells[oldX -1][oldY +1].setContent(BoardCell.Content.EMPTY);
                    }else if ( oldX == 7) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                        if(boardCells[oldX -1][oldY +1].getContent() == BoardCell.Content.WHITE_PAWN) {
                            boardCells[oldX -1][oldY +1].setContent(BoardCell.Content.EMPTY);
                        }
                    }else if (oldX + 1 == 7) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                    }else if (oldX == 1) {
                        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                        if(boardCells[oldX +1][oldY +1].getContent() == BoardCell.Content.WHITE_PAWN) {
                            boardCells[oldX +1][oldY +1].setContent(BoardCell.Content.EMPTY);
                        }
                    }else if (boardCells[oldX - 1][oldY + 1].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX - 2][oldY + 2].getContent() == BoardCell.Content.BLUE_PLACE) {
                        boardCells[oldX - 1][oldY + 1].setContent(BoardCell.Content.EMPTY);
                    }else if (boardCells[oldX + 1][oldY + 1].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX + 2][oldY + 2].getContent() == BoardCell.Content.BLUE_PLACE) {
                        boardCells[oldX + 1][oldY + 1].setContent(BoardCell.Content.EMPTY);
                    }
                    boardCells[newX][newY].setContent(BoardCell.Content.RED_PAWN);
                    boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                }

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
            isSelect = true;

        }
    }

    public static void movePawn (Board boardCellsReady , int oldX, int oldY,int pawnType) {
        BoardCell boardCells[][] = boardCellsReady.getBoardCells();
        BoardCell boardCellWithRedPawn = new BoardCell(-pawnType);
        BoardCell boardCellWithWhitePawn = new BoardCell(pawnType);

        if ((oldX == 0  && boardCells[oldX +1][oldY -1*pawnType].getContent() == BoardCell.Content.EMPTY)) {
            boardCells[oldX +1][oldY -1*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        } else if((oldX == 0 && boardCells[oldX +1][oldY -1*pawnType].getContent() == new BoardCell(-pawnType).getContent() &&
                boardCells[oldX + 2][oldY -2*pawnType].getContent() == BoardCell.Content.EMPTY)) {
            boardCells[oldX + 2][oldY -2*pawnType].setContent(BoardCell.Content.BLUE_PLACE); //XDDDDDDDDDDDDDDDDDDDDDDDD
        }else if (oldX == 1 && boardCells[oldX -1][oldY -1*pawnType].getContent() != BoardCell.Content.EMPTY && boardCells[oldX +1][oldY -1*pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX +1][oldY -1*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        }else if (oldX == 7 && boardCells[oldX -1][oldY -1*pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX -1][oldY -1*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        } else if(oldX != 7 && oldX != 0 && boardCells[oldX -1][oldY -1 * pawnType].getContent() == BoardCell.Content.EMPTY && boardCells[oldX + 1][oldY -1 * pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX -1][oldY -1 * pawnType].setContent(BoardCell.Content.BLUE_PLACE);
            boardCells[oldX + 1][oldY -1 * pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (boardCells[oldX -1][oldY -1 *pawnType].getContent() == new BoardCell(-pawnType).getContent() && boardCells[oldX -2][oldY -2*pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX -2][oldY -2*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        }else if (boardCells[oldX +1][oldY -1*pawnType].getContent() != new BoardCell(pawnType).getContent() && boardCells[oldX +2][oldY -2*pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX +2][oldY -2*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        }else if ((boardCells[oldX -1][oldY -1*pawnType].getContent() != BoardCell.Content.EMPTY) && boardCells[oldX +1][oldY -1*pawnType].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX +1][oldY -1*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (boardCells[oldX +1][oldY -1*pawnType].getContent() != BoardCell.Content.EMPTY &&boardCells[oldX -1][oldY -1*pawnType].getContent() == BoardCell.Content.EMPTY ) {
            boardCells[oldX -1][oldY -1*pawnType].setContent(BoardCell.Content.BLUE_PLACE);
        }
        redrawBoard(boardCellsReady);
        isSelect = false;
        if(pawnType == 1) {
            whitePawnTurn = false;
        } else {
            whitePawnTurn = true;
        }
    }

    private static void redrawBoard(Board board) {
        CheckersApp.boardGroup.getChildren().removeAll();
        List<BoardElement> elementsToDraw = boardDrawer.draw(board);
        addListeners(elementsToDraw, board);
        CheckersApp.boardGroup.getChildren().addAll(elementsToDraw);
    }


}



