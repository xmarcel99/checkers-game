package com.checkers;

import java.util.Random;

import static com.checkers.MovingPawns.findAllowedPlacesForKing;
import static com.checkers.MovingPawns.isEmptyCell;

public class ComputerMovement {

    private static boolean isEnemyPawn(int oldX, int oldY, int i, int i2, BoardCell[][] boardCells) {
        if (oldX - i < 0 || oldX - i > 7 || oldY - i2 < 0 || oldY - i2 > 7) {
            return false;
        }
        return boardCells[oldX - i][oldY - i2].getContent().getContentInInt() == 1;
    }
    public static void findAllowedPlacesForComputerKing(int oldX, int oldY,BoardCell[][] boardCells, int k, int m, int v, int g, BoardCell.Content content) {
        boolean isTrue = true;
        for (int i = k; isTrue; i += content == BoardCell.Content.WHITE_PAWN ? +1 : -1) {
            if (isEmptyCell(oldX, oldY, i, i * v, boardCells)) {
                if (boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING ||
                        boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.WHITE_KING && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    boardCells[oldX - i][oldY - i * v].setContent(BoardCell.Content.RED_KING);
                    break;
                } else if(boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    break;
                } else {
                    boardCells[oldX - i][oldY - i * v].setContent(BoardCell.Content.RED_KING);
                }
            }
            if (content == BoardCell.Content.WHITE_PAWN) {
                isTrue = i < m;
            } else if (content == BoardCell.Content.RED_PAWN) {
                isTrue = i > m;
            }
        }
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
                        findAllowedPlacesForComputerKing(oldX,oldY,boardCells, 1, 8, 1, 1, BoardCell.Content.WHITE_PAWN);
                        findAllowedPlacesForComputerKing(oldX,oldY,boardCells, -1, -8, 1, 1, BoardCell.Content.RED_PAWN);
                        //LEWY DÓŁ
                        findAllowedPlacesForComputerKing(oldX,oldY,boardCells, 1, 8, -1, -1, BoardCell.Content.WHITE_PAWN);
                        //PRAWA GÓRA
                        findAllowedPlacesForComputerKing(oldX,oldY,boardCells, -1, -8, -1, -1, BoardCell.Content.RED_PAWN);
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
