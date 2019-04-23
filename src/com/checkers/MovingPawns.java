package com.checkers;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class MovingPawns {

    private static int oldX;
    private static int oldY;
    private static int newX;
    private static int newY;
    private static boolean isSelect = true;
    private static BoardDrawer boardDrawer = new BoardDrawer();
    private static boolean whitePawnTurn = true;
    private static int canLoopGo = 0;


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
            eventShowingAllowedPawnMoves(boardCells,board);
        } else {
            newX = (int) event.getSceneX() / 100;
            newY = (int) event.getSceneY() / 100;
            eventMovingPawnToAllowedPlace(boardCells);
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

    private static void showAllowedPawnMoves(Board boardCellsReady, int oldX, int oldY) {
        BoardCell[][] boardCells = boardCellsReady.getBoardCells();

        if (isStillWhitePawnTurn(whitePawnTurn, oldX, oldY, boardCells)) {
            isSelect = true;
        } else if (isStillRedPawnTurn(whitePawnTurn, oldX, oldY, boardCells)) {
            isSelect = true;
        } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING || boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING) {
           showAllowedPawnMovesForKing(boardCells);
        } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN || boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
            showAllowedPawnMovesForNotKing(boardCells,boardCells[oldX][oldY].getContent());
        }
    }

    private static void movePawnToBluePlace(int newX, int newY, int oldX, int oldY, BoardCell.Content content) {
        BoardCell[][] boardCells = CheckersApp.readyBoard.getBoardCells();

        if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN || boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
            if (oldX == 7) {
                boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                if (boardCells[oldX - 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt()) {
                    boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
                }
            } else if (oldX > 0 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt()
                    && isBlueCell(2, 2 * content.getContentInInt(), boardCells)) {
                if (isBlueCell(2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
                }
            } else if (oldX + 1 == 7) {
                boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
            } else if (boardCells[oldX + 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt()
                    && isBlueCell(-2, 2 * content.getContentInInt(), boardCells)  && isBlueCell(-2, 2 * content.getContentInInt(), boardCells)) {
                    boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.EMPTY);
            }


            if (content == BoardCell.Content.WHITE_PAWN) {
                if (newY == 0) {
                    boardCells[newX][newY].setContent(BoardCell.Content.WHITE_KING);
                } else {
                    boardCells[newX][newY].setContent(BoardCell.Content.WHITE_PAWN);
                }
            } else if (content == BoardCell.Content.RED_PAWN) {
                if (newY == 7) {
                    boardCells[newX][newY].setContent(BoardCell.Content.RED_KING);
                } else {
                    boardCells[newX][newY].setContent(BoardCell.Content.RED_PAWN);
                }

            }
            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
        } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING || boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {

            if (isBlueCellForKings(-2, -2, boardCells)) {
                boardCells[newX + 1][newY + 1].setContent(BoardCell.Content.EMPTY);
            } else if (isBlueCellForKings(2, 2, boardCells)) {
                boardCells[newX - 1][newY - 1].setContent(BoardCell.Content.EMPTY);
            } else if (isBlueCellForKings(-2, 2, boardCells)) {
                boardCells[newX + 1][newY - 1].setContent(BoardCell.Content.EMPTY);
            } else if (isBlueCellForKings(2, -2, boardCells)) {
                boardCells[newX - 1][newY + 1].setContent(BoardCell.Content.EMPTY);
            }
            if (whitePawnTurn) {
                boardCells[newX][newY].setContent(BoardCell.Content.RED_KING);
            } else {
                boardCells[newX][newY].setContent(BoardCell.Content.WHITE_KING);
            }
            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);

        }

    }
    private static void showAllowedPawnMovesForNotKing (BoardCell[][] boardCells, BoardCell.Content content) {
        if ((oldX == 0 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY)) {
            boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if ((oldX == 0 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt() && isEmptyCell(-2, 2 * content.getContentInInt(), boardCells))) {
            boardCells[oldX + 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX == 1 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX == 7 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX != 7 && oldX != 0 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
            boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX > 1 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt() && isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                boardCells[oldX - 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX < 6 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt() && isEmptyCell(-2, 2 * content.getContentInInt(), boardCells)) {
                boardCells[oldX + 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX > 0 && oldX < 7 && (boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY) && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX + 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX > 0 && oldX < 7 && boardCells[oldX + 1][oldY - content.getContentInInt()].getContent() != BoardCell.Content.EMPTY && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent() == BoardCell.Content.EMPTY) {
            boardCells[oldX - 1][oldY - content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        } else if (oldX == 7 && boardCells[oldX - 1][oldY - content.getContentInInt()].getContent().getContentInInt() == content.getOppositeContent().getContentInInt() && isEmptyCell(2, 2 * content.getContentInInt(), boardCells)) {
                boardCells[oldX - 2][oldY - 2 * content.getContentInInt()].setContent(BoardCell.Content.BLUE_PLACE);
        }
        changePlayerTurn(boardCells[oldX][oldY].getContent());
    }
    private static void eventMovingPawnToAllowedPlace( BoardCell[][] boardCells) {
        if (boardCells[newX][newY].getContent() == BoardCell.Content.BLUE_PLACE) {

            if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN) {
                movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.WHITE_PAWN);
            } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING) {
                movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.WHITE_KING);
            } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN) {
                movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.RED_PAWN);
            } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                movePawnToBluePlace(newX, newY, oldX, oldY, BoardCell.Content.RED_KING);
            }


        } else if (boardCells[newX][newY].getContent() != BoardCell.Content.BLUE_PLACE) {
            if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_PAWN ||boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ) {
                whitePawnTurn = true;
            } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_PAWN || boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                whitePawnTurn = false;
            }
        }
    }
    private static void eventShowingAllowedPawnMoves ( BoardCell [][] boardCells, Board board) {
        if (boardCells[oldX][oldY].getContent() != BoardCell.Content.EMPTY) {
                showAllowedPawnMoves(board, oldX, oldY);
            }
    }
    private static void showAllowedPawnMovesForKing(BoardCell[][] boardCells) {
        findAllowedPlacesForKing(boardCells,1,8,1,1, BoardCell.Content.WHITE_PAWN);
        findAllowedPlacesForKing(boardCells,-1,-8,1,1, BoardCell.Content.RED_PAWN);
        findAllowedPlacesForKing(boardCells,1,8,-1,-1, BoardCell.Content.WHITE_PAWN);
        findAllowedPlacesForKing(boardCells,-1,-8,-1,-1, BoardCell.Content.RED_PAWN);
        changePlayerTurn(boardCells[oldX][oldY].getContent());
    }
    private static  void changePlayerTurn (BoardCell.Content content) {
        redrawBoard(CheckersApp.readyBoard);
        isSelect = false;
        whitePawnTurn = content.getContentInInt() != 1;
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

    private static boolean isBlueCellForKings(int i, int i2, BoardCell[][] boardCells) {
        if (newX - i < 0 || newX - i > 7 || newY - i2 < 0 || newY - i2 > 7) {
            return false;
        }
        return boardCells[newX - i][newY - i2].getContent() == BoardCell.Content.BLUE_PLACE || boardCells[newX - i][newY - i2].getContent() == BoardCell.Content.RED_KING
                || boardCells[newX - i][newY - i2].getContent() == BoardCell.Content.WHITE_KING;
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
        if (boardCells[oldX][oldY].getContent() != BoardCell.Content.RED_PAWN && !isRedPawnTurn && boardCells[oldX][oldY].getContent() != BoardCell.Content.RED_KING) {
            isStillRedPawnTurn = true;
        }
        return isStillRedPawnTurn;
    }

    public static void findAllowedPlacesForKing (BoardCell[][] boardCells, int k,int m,int v,int g, BoardCell.Content content) {
        boolean isTrue = true;
        for (int i = k; isTrue ; i += content == BoardCell.Content.WHITE_PAWN ? +1 : -1) {
            if (isEmptyCell(i, i*v, boardCells)) {
                if(boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING||
                        boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ||
                        boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.RED_KING && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ||
                        boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.WHITE_KING && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    boardCells[oldX - i][oldY - i*v].setContent(BoardCell.Content.BLUE_PLACE);
                    break;
                }else if (boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING||
                        boardCells[oldX -i+content.getContentInInt()][oldY -i*v+content.getContentInInt()*g].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    break;
                }else {
                    boardCells[oldX - i][oldY - i*v].setContent(BoardCell.Content.BLUE_PLACE);
                }
            }
            if(content == BoardCell.Content.WHITE_PAWN) {
                isTrue = i < m;
            }else if (content == BoardCell.Content.RED_PAWN) {
                isTrue = i > m;
            }
        }

    }
}


