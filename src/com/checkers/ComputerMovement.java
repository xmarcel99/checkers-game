package com.checkers;

import java.util.Random;

import static com.checkers.MovingPawns.isEmptyCell;

public class ComputerMovement {

    private static boolean isEnemyPawn(int oldX, int oldY, int i, int i2, BoardCell[][] boardCells) {
        if (oldX - i < 0 || oldX - i > 7 || oldY - i2 < 0 || oldY - i2 > 7) {
            return false;
        }
        return boardCells[oldX - i][oldY - i2].getContent().getContentInInt() == 1;
    }

    public static void computerMovement(Board board) {
        boolean canLoopGo = true;
        BoardCell[][] boardCells = board.getBoardCells();
        for (BoardCell[] x : board.getBoardCells()) {
            for (BoardCell y : x) {
                if (y.getContent().getContentInInt() == -1) {
                    int oldX = y.getX();
                    int oldY = y.getY();
                    if (y.getContent() == BoardCell.Content.RED_PAWN) {
                        if (isEnemyPawn(oldX, oldY, 1, -1, boardCells) && isEmptyCell(oldX, oldY, 2, -2, boardCells)) {
                            if (oldY + 2 == 7) {
                                boardCells[oldX - 2][oldY + 2].setContent(BoardCell.Content.RED_KING);
                            } else {
                                boardCells[oldX - 2][oldY + 2].setContent(BoardCell.Content.RED_PAWN);
                            }
                            boardCells[oldX - 1][oldY + 1].setContent(BoardCell.Content.EMPTY);
                            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                            canLoopGo = false;
                            break;
                        } else if (isEnemyPawn(oldX, oldY, -1, -1, boardCells) && isEmptyCell(oldX, oldY, -2, -2, boardCells)) {
                            if (oldY + 2 == 7) {
                                boardCells[oldX + 2][oldY + 2].setContent(BoardCell.Content.RED_KING);
                            } else {
                                boardCells[oldX + 2][oldY + 2].setContent(BoardCell.Content.RED_PAWN);
                            }
                            boardCells[oldX + 1][oldY + 1].setContent(BoardCell.Content.EMPTY);
                            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                            canLoopGo = false;
                            break;
                        } else if (isEmptyCell(oldX, oldY, -1, -1, boardCells)) {
                            if (oldY + 1 == 7) {
                                boardCells[oldX + 1][oldY + 1].setContent(BoardCell.Content.RED_KING);
                            } else {
                                boardCells[oldX + 1][oldY + 1].setContent(BoardCell.Content.RED_PAWN);
                            }
                            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                            canLoopGo = false;
                            break;
                        } else if (isEmptyCell(oldX, oldY, 1, -1, boardCells)) {
                            if (oldY + 1 == 7) {
                                boardCells[oldX - 1][oldY + 1].setContent(BoardCell.Content.RED_KING);
                            } else {
                                boardCells[oldX - 1][oldY + 1].setContent(BoardCell.Content.RED_PAWN);
                            }
                            boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
                            canLoopGo = false;
                            break;
                        }
                    } else if (y.getContent() == BoardCell.Content.RED_KING) {
                        Random random = new Random();
                        int wayForKingNumber = random.nextInt(4);
                        System.out.println(wayForKingNumber);
                        if(wayForKingNumber == 0) {
                            for (int i = 1; i < 8; i++) {

                                while (isEmptyCell(oldX, oldY, i, i, boardCells)) {
                                    boardCells[oldX - i][oldY - i].setContent(BoardCell.Content.RED_KING);
                                    boardCells[oldX - i + 1][oldY - i + 1].setContent(BoardCell.Content.EMPTY);
                                }
                            }

                        } else if ( wayForKingNumber == 1){
                            for (int i = -1; i > -8; i--) {

                                while (isEmptyCell(oldX, oldY, i, i, boardCells)) {
                                    boardCells[oldX - i][oldY - i].setContent(BoardCell.Content.RED_KING);
                                    boardCells[oldX - i - 1][oldY - i - 1].setContent(BoardCell.Content.EMPTY);
                                }
                            }
                        }else if (wayForKingNumber == 2) {
                            for (int i = 1; i < 8; i++) {

                                while (isEmptyCell(oldX, oldY, i, i * -1, boardCells)) {
                                    boardCells[oldX - i][oldY - i * -1].setContent(BoardCell.Content.RED_KING);
                                    boardCells[oldX - i + 1][oldY - (i - 1) * -1].setContent(BoardCell.Content.EMPTY);
                                }
                            }
                        }else if (wayForKingNumber == 3) {
                            for (int i = -1; i > -8; i--) {

                                while (isEmptyCell(oldX, oldY, i, i * -1, boardCells)) {
                                    boardCells[oldX - i][oldY - i * -1].setContent(BoardCell.Content.RED_KING);
                                    boardCells[oldX - i - 1][oldY - (i + 1) * -1].setContent(BoardCell.Content.EMPTY);
                                }
                            }
                        }
                        canLoopGo = false;
                    }
                }
            }
            if (!canLoopGo) {
                break;
            }
        }
        try {
            SaveAndLoadGameProgress.saveGameProgress(boardCells, "1.save");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        MovingPawns.whitePawnTurn = true;
    }
}
