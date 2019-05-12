package com.checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.checkers.MovingPawns.isEmptyCell;

public class ComputerMovement {
    private static int newX;
    private static int newY;
    static List<CoordinatesOfAllowedPlacesToMove> computerDoubleKillList = new ArrayList<>();

    private static boolean isEnemyPawn(int oldX, int oldY, int i, int i2, BoardCell[][] boardCells) {
        if (oldX - i < 0 || oldX - i > 7 || oldY - i2 < 0 || oldY - i2 > 7) {
            return false;
        }
        return boardCells[oldX - i][oldY - i2].getContent().getContentInInt() == 1;
    }

    private static void randomAllowedPlaceForComputerKing(int oldX, int oldY, BoardCell[][] boardCells) {
        List<CoordinatesOfAllowedPlacesToMove> allowedPlacesToMove = AllowedPlacesForKing.getAllowedPlacesToMove();
        Random random = new Random();
        int randomNumber = random.nextInt(allowedPlacesToMove.size());
        CoordinatesOfAllowedPlacesToMove randomAllowedPlace = allowedPlacesToMove.get(randomNumber);
        boardCells[randomAllowedPlace.getNewX()][randomAllowedPlace.getNewY()].setContent(BoardCell.Content.RED_KING);
        if (randomAllowedPlace.isCapturing()) {
            boardCells[randomAllowedPlace.getCapturedPawnX()][randomAllowedPlace.getCapturedPawnY()].setContent(BoardCell.Content.EMPTY);
        }
        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
        allowedPlacesToMove.clear();
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
                            newX = oldX - 2;
                            newY = oldY + 2;
                            computerDoubleKillList.add(new CoordinatesOfAllowedPlacesToMove(oldX - 2, oldY + 2, true, oldX - 1, oldY + 1));
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
                            newX = oldX + 2;
                            newY = oldY + 2;
                            computerDoubleKillList.add(new CoordinatesOfAllowedPlacesToMove(oldX + 2, oldY + 2, true, oldX + 1, oldY + 1));
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
                        kingMovement(boardCells, oldX, oldY);
                        randomAllowedPlaceForComputerKing(oldX, oldY, boardCells);
                        canLoopGo = false;
                        break;
                    }
                }
            }
            if (!canLoopGo) {
                break;
            }
        }
        if (computerDoubleKillList.size() == 1 && computerDoubleKillList.get(0).isCapturing()) {
            while (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, 2, -2, 1, -1, BoardCell.Content.RED_PAWN) ||
                    AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, -2, -2, -1, -1, BoardCell.Content.RED_PAWN)) {
                if (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, 2, -2, 1, -1, BoardCell.Content.RED_PAWN)) {
                    if(newY + 2 == 7) {
                        boardCells[newX - 2][newY + 2].setContent(BoardCell.Content.RED_KING);
                    } else {
                        boardCells[newX - 2][newY + 2].setContent(BoardCell.Content.RED_PAWN);
                    }
                    boardCells[newX - 1][newY + 1].setContent(BoardCell.Content.EMPTY);
                } else if (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, -2, -2, -1, -1, BoardCell.Content.RED_PAWN)) {
                    if(newY + 2 == 7) {
                        boardCells[newX + 2][newY + 2].setContent(BoardCell.Content.RED_KING);
                    }else {
                        boardCells[newX + 2][newY + 2].setContent(BoardCell.Content.RED_PAWN);
                    }
                    boardCells[newX + 1][newY + 1].setContent(BoardCell.Content.EMPTY);
                }
                boardCells[newX][newY].setContent(BoardCell.Content.EMPTY);
            }
        }
        computerDoubleKillList.clear();
        SaveAndLoadGameProgress.saveGameProgress(boardCells, "1.save");
        MovingPawns.whitePawnTurn = true;
    }

    public static void kingMovement(BoardCell[][] boardCells, int oldX, int oldY) {
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, 1, 8, 1, 1, BoardCell.Content.WHITE_PAWN, oldX, oldY, 1, 1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, -1, -8, 1, 1, BoardCell.Content.RED_PAWN, oldX, oldY, -1, -1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, 1, 8, -1, -1, BoardCell.Content.WHITE_PAWN, oldX, oldY, 1, -1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, -1, -8, -1, -1, BoardCell.Content.RED_PAWN, oldX, oldY, -1, 1);
    }
}