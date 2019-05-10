package com.checkers;

import java.util.ArrayList;
import java.util.List;

import static com.checkers.MovingPawns.isEmptyCell;

public class AllowedPlacesForKing {
    private static List<CoordinatesOfAllowedPlacesToMove> allowedPlacesToMove = new ArrayList<>();

    public static List<CoordinatesOfAllowedPlacesToMove> getAllowedPlacesToMove() {
        return allowedPlacesToMove;
    }

    public static void findAllowedPlacesForKing(BoardCell[][] boardCells, int k, int m, int v, int g, BoardCell.Content content, int oldX, int oldY, int w, int z) {
        boolean isTrue = true;

        for (int i = k; isTrue; i += content == BoardCell.Content.WHITE_PAWN ? +1 : -1) {
            int newX = oldX - i;
            int newY = oldY - i * v;
            if (isEmptyCell(oldX, oldY, i, i * v, boardCells)) {
                if (boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING ||
                        boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ||
                        boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.RED_KING && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ||
                        boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.WHITE_KING && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING) {
                        boardCells[oldX - i][oldY - i * v].setContent(BoardCell.Content.BLUE_PLACE);
                    } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                        allowedPlacesToMove.add(new CoordinatesOfAllowedPlacesToMove(newX, newY, true, newX + w, newY + z));
                    }
                    break;
                } else if (boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.WHITE_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING ||
                        boardCells[oldX - i + content.getContentInInt()][oldY - i * v + content.getContentInInt() * g].getContent() == BoardCell.Content.RED_PAWN && boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                    break;
                } else {
                    if (boardCells[oldX][oldY].getContent() == BoardCell.Content.WHITE_KING) {
                        boardCells[oldX - i][oldY - i * v].setContent(BoardCell.Content.BLUE_PLACE);
                    } else if (boardCells[oldX][oldY].getContent() == BoardCell.Content.RED_KING) {
                        allowedPlacesToMove.add(new CoordinatesOfAllowedPlacesToMove(newX, newY, false));
                    }
                }
            }
            if (content == BoardCell.Content.WHITE_PAWN) {
                isTrue = i < m;
            } else if (content == BoardCell.Content.RED_PAWN) {
                isTrue = i > m;
            }
        }
    }
}
