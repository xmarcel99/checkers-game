package com.checkers;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class MovingPawns {

    private static int oldX;
    private static int oldY;
    private static boolean isSelect = true;
    private static BoardDrawer boardDrawer = new BoardDrawer();
    private static boolean whitePawnTurn = true;

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
                if(boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
                    showAllowedPawnMoves(board, oldX, oldY, BoardCell.Content.WHITE_PAWN);
                } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING) {
                    showAllowedPawnMoves(board,oldX,oldY, BoardCell.Content.WHITE_KING);
                }
            } else {
                if(boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN) {
                    showAllowedPawnMoves(board, oldX, oldY, BoardCell.Content.RED_PAWN);
                } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    showAllowedPawnMoves(board,oldX,oldY, BoardCell.Content.RED_KING);
                }
            }
        } else {

            int newX = (int) event.getSceneX() / 100;
            int newY = (int) event.getSceneY() / 100;

            if (boardCells[newX][newY].getContent() == BoardCell.Content.BLUE_PLACE) {

                if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
                    movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.WHITE_PAWN);
                } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN) {
                    movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.RED_PAWN);
                }


            } else if (boardCells[newX][newY].getContent() != BoardCell.Content.BLUE_PLACE) {
                if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
                    whitePawnTurn = true;
                } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN) {
                    whitePawnTurn = false;
                }
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
            isSelect = true;

        }
    }

    private static void showAllowedPawnMoves(Board boardCellsReady, int oldX, int oldY, BoardCell.Content content) {
        BoardCell[][] boardCells = boardCellsReady.getBoardCells();

        if (isStillWhitePawnTurn(whitePawnTurn, oldX, oldY, boardCells)) {
            isSelect = true;
        } else if (isStillRedPawnTurn(whitePawnTurn, oldX, oldY, boardCells)) {
            isSelect = true;
        } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
            if (boardCells[oldX - 1][oldY - 1].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX - 1][oldY - 1].setContent(BoardCell.Content.BLUE_PLACE);
            }
            redrawBoard(boardCellsReady);
            isSelect = false;
        } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN || boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
            if ((oldX == 0 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY)) {
                boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if ((oldX == 0 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isEmptyCell(-2, 2 * content.getContentInInt(), boardCells))) {
                if (isEmptyCell(-2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX + 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
                }
            } else if (oldX == 1 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if (oldX == 7 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if (oldX != 7 && oldX != 0 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
                boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if (oldX > 1 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                if (isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX - 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
                }
            } else if (oldX < 6 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isEmptyCell(-2, 2 * content.getContentInInt(), boardCells)) {
                if (isEmptyCell(-2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX + 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
                }
            } else if (oldX > 0 && oldX < 7 && (boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY) && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if (oldX > 0 && oldX < 7 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
                boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            } else if (oldX == 7 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                if (isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX - 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
                }
            }
            redrawBoard(boardCellsReady);
            isSelect = false;
            whitePawnTurn = content.getContentInInt() != 1;
        }
    }

    private static void movePawnToBluePlace(int newX, int newY, int oldX, int oldY, BoardCell.Content content) {
        BoardCell[][] boardCells = CheckersApp.readyBoard.getBoardCells();


        if (oldX == 7) {
            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
            if (boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent()) {
                boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
            }
        } else if (oldX > 0 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isBlueCell(2, 2 * content.getContentInInt(), boardCells)) {
            if (isBlueCell(2, 2 * content.getContentInInt(), boardCells)) {
                boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
            }
        } else if (oldX + 1 == 7) {
            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
        } else if (boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == content.getOppositeContent() && isBlueCell(-2, 2 * content.getContentInInt(), boardCells)) {
            if (isBlueCell(-2, 2 * content.getContentInInt(), boardCells)) {
                boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
            }
        }


        if (content.getContentInInt() == 1) {
            if (newY == 0) {
                boardCells[newX][newY].setContent(BoardCell.Content.WHITE_KING);
            } else {
                boardCells[newX][newY].setContent(BoardCell.Content.WHITE_PAWN);
            }
        } else if (content.getContentInInt() == -1) {
            if (newY == 7) {
                boardCells[newX][newY].setContent(BoardCell.Content.RED_KING);
            } else {
                boardCells[newX][newY].setContent(BoardCell.Content.RED_PAWN);
            }

        }
        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
    }

    private static void redrawBoard(Board board) {
        CheckersApp.boardGroup.getChildren().removeAll();
        List<BoardElement> elementsToDraw = boardDrawer.draw(board);
        addListeners(elementsToDraw, board);
        CheckersApp.boardGroup.getChildren().addAll(elementsToDraw);
    }

    private static boolean isEmptyCell(int i, int i2, BoardCell[][] boardCells) {
        if (oldX - i < 0 || oldX - i > 7 || oldY - i2 < 0 || oldY - i2 > 7) {
            return false;
        }
        return boardCells[oldX - i][oldY - i2].getContent() == BoardCell.Content.EMPTY;
    }

    private static boolean isBlueCell(int i, int i2, BoardCell[][] boardCells) {
        if (oldX - i < 0 || oldX - i > 7 || oldY - i2 < 0 || oldY - i2 > 7) {
            return false;
        }
        return boardCells[oldX - i][oldY - i2].getContent() == BoardCell.Content.BLUE_PLACE;
    }

    private static boolean isStillWhitePawnTurn(boolean isWhitePawnTurn, int oldX, int oldY, BoardCell[][] boardCells) {
        boolean isStillWhitePawnTurn = false;
        if (boardCells[oldX][oldY].getContent() != BoardCell.Content.WHITE_PAWN && isWhitePawnTurn && boardCells[oldX][oldY].getContent() != BoardCell.Content.WHITE_KING) {
             isStillWhitePawnTurn = true;
        }
        return isStillWhitePawnTurn;

    }

    private static boolean isStillRedPawnTurn(boolean isRedPawnTurn, int oldX, int oldY, BoardCell[][] boardCells) {
        boolean isStillRedPawnTurn = false;
        if(boardCells[oldX][oldY].getContent() != BoardCell.Content.RED_PAWN && !isRedPawnTurn && boardCells[oldX][oldY].getContent() != BoardCell.Content.RED_KING){
            isStillRedPawnTurn = true;
        }
        return  isStillRedPawnTurn;
    }
}


