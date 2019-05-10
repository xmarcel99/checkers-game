package com.checkers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.checkers.MovingPawns.redrawBoard;

public class SaveAndLoadGameProgress {

    public static void saveGameProgress(Serializable data, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Saving...");
        }
    }

    public static Object loadGameProgress(String fileName) throws Exception {

        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
            return ois.readObject();
        } finally {
            System.out.println("Loading...");
        }

    }

    public static void loadGame(Board readyBoard) {
        File file1 = new File("2.save");
        try {
            BoardCell[][] boardCells = (BoardCell[][]) SaveAndLoadGameProgress.loadGameProgress("1.save");
            Board.setBoardCells(boardCells);
            MovingPawns.removeAllBluePlacesForBoard(readyBoard);
            redrawBoard(readyBoard);
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {

                    BoardCell boardCell = null;
                    if ((x + y) % 2 == 0) {
                        boardCell = readyBoard.getBoardCells()[x][y];

                    } else {
                        if (y <= 2) {
                            boardCell = readyBoard.getBoardCells()[x][y];
                        }
                        if (y >= 5) {
                            boardCell = readyBoard.getBoardCells()[x][y];
                        }
                        if (y > 2 && y < 5) {
                            boardCell = readyBoard.getBoardCells()[x][y];
                        }
                    }
                    boardCells[x][y] = boardCell;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (file1.exists() && file1.isFile()) {
            try {
                MovingPawns.whitePawnTurn = ((boolean) SaveAndLoadGameProgress.loadGameProgress("2.save"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void newGame(Board readyBoard) {
        MovingPawns.whitePawnTurn = true;
        BoardCell[][] boardCells = readyBoard.getBoardCells();

        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) {

                BoardCell boardCell = null;
                if ((x + y) % 2 == 0) {
                    boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.WHITE, x, y);
                } else {
                    if (y <= 2) {
                        boardCell = new BoardCell(BoardCell.Content.RED_PAWN, BoardCell.Color.BLACK, x, y);
                    }
                    if (y >= 5) {
                        boardCell = new BoardCell(BoardCell.Content.WHITE_PAWN, BoardCell.Color.BLACK, x, y);
                    }
                    if (y > 2 && y < 5) {
                        boardCell = new BoardCell(BoardCell.Content.EMPTY, BoardCell.Color.BLACK, x, y);
                    }
                }
                boardCells[x][y] = boardCell;
            }
    }
}
