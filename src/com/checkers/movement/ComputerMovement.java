package com.checkers.movement;

import com.checkers.board.Board;
import com.checkers.board.BoardCell;
import com.checkers.gameProgress.SaveAndLoadGameProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.checkers.movement.MovingPawns.isEmptyCell;

public class ComputerMovement {


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

    public static void computerKillPawnMovement(BoardCell[][] boardCells, int oldX, int oldY, int newX, int newY, int capturedPawnX, int capturedPawnY, int i) {
        List<CoordinatesOfAllowedPlacesToMove> computerDoubleKillList = new ArrayList<>();
        if (oldY + 2 == 7) {
            boardCells[newX][newY].setContent(BoardCell.Content.RED_KING);
        } else {
            boardCells[newX][newY].setContent(BoardCell.Content.RED_PAWN);
        }
        newX = oldX + i;
        newY = oldY + 2;
        computerDoubleKillList.add(new CoordinatesOfAllowedPlacesToMove(newX, newY, true, capturedPawnX, capturedPawnY));
        boardCells[capturedPawnX][capturedPawnY].setContent(BoardCell.Content.EMPTY);
        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
        doubleKill(boardCells, computerDoubleKillList, newX, newY, BoardCell.Content.RED_PAWN, BoardCell.Content.RED_KING, -2, -1, 2, 1, -2, 2, newY + 2 == 7);
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
                            computerKillPawnMovement(boardCells, oldX, oldY, oldX - 2, oldY + 2, oldX - 1, oldY + 1, -2);
                            canLoopGo = false;
                            break;
                        } else if (isEnemyPawn(oldX, oldY, -1, -1, boardCells) && isEmptyCell(oldX, oldY, -2, -2, boardCells)) {
                            computerKillPawnMovement(boardCells, oldX, oldY, oldX + 2, oldY + 2, oldX + 1, oldY + 1, 2);
                            canLoopGo = false;
                            break;
                        } else if (isEmptyCell(oldX, oldY, -1, -1, boardCells)) {
                            computerNormalPawnMovement(oldX, oldY, boardCells, 1);
                            canLoopGo = false;
                            break;
                        } else if (isEmptyCell(oldX, oldY, 1, -1, boardCells)) {
                            computerNormalPawnMovement(oldX, oldY, boardCells, -1);
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
        SaveAndLoadGameProgress.saveGameProgress(boardCells, "1.save");
        MovingPawns.whitePawnTurn = true;
    }

    public static void kingMovement(BoardCell[][] boardCells, int oldX, int oldY) {
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, 1, 8, 1, 1, BoardCell.Content.WHITE_PAWN, oldX, oldY, 1, 1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, -1, -8, 1, 1, BoardCell.Content.RED_PAWN, oldX, oldY, -1, -1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, 1, 8, -1, -1, BoardCell.Content.WHITE_PAWN, oldX, oldY, 1, -1);
        AllowedPlacesForKing.findAllowedPlacesForKing(boardCells, -1, -8, -1, -1, BoardCell.Content.RED_PAWN, oldX, oldY, -1, 1);
    }

    public static void computerNormalPawnMovement(int oldX, int oldY, BoardCell[][] boardCells, int w) {
        if (oldY + 1 == 7) {
            boardCells[oldX + w][oldY + 1].setContent(BoardCell.Content.RED_KING);
        } else {
            boardCells[oldX + w][oldY + 1].setContent(BoardCell.Content.RED_PAWN);
        }
        boardCells[oldX][oldY].setContent(BoardCell.Content.EMPTY);
    }

    public static void doubleKill(BoardCell[][] boardCells, List<CoordinatesOfAllowedPlacesToMove> list, int newX, int newY, BoardCell.Content content1,
                                  BoardCell.Content content2, int i2, int e2, int j, int k, int w, int g, boolean ifStatement) {
        while (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, 2, i2, 1, e2, content1) ||
                AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, -2, i2, -1, e2, content1)) {
            if (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, 2, i2, 1, e2, content1)) {
                if (ifStatement) {
                    boardCells[newX + w][newY + j].setContent(content2);
                } else {
                    boardCells[newX + w][newY + j].setContent(content1);
                }
                boardCells[newX + e2][newY + k].setContent(BoardCell.Content.EMPTY);
            } else if (AllowedPlacesForKing.isAnyKillMove(boardCells, newX, newY, -2, -2, -1, -1, content1)) {
                if (ifStatement) {
                    boardCells[newX + g][newY + j].setContent(content2);
                } else {
                    boardCells[newX + g][newY + j].setContent(content1);
                }
                boardCells[newX + k][newY + k].setContent(BoardCell.Content.EMPTY);
            }
            boardCells[newX][newY].setContent(BoardCell.Content.EMPTY);
        }
        list.clear();
    }
}