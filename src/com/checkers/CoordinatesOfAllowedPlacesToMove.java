package com.checkers;

public class CoordinatesOfAllowedPlacesToMove {
    private int newX;
    private int newY;
    private boolean capturing;
    private int capturedPawnX;
    private int capturedPawnY;

    public CoordinatesOfAllowedPlacesToMove(int newX, int newY, boolean capturing) {
        this.newX = newX;
        this.newY = newY;
        this.capturing = capturing;
    }

    public CoordinatesOfAllowedPlacesToMove(int newX, int newY, boolean capturing, int capturedPawnX, int capturedPawnY) {
        this.newX = newX;
        this.newY = newY;
        this.capturing = capturing;
        this.capturedPawnX = capturedPawnX;
        this.capturedPawnY = capturedPawnY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public int getCapturedPawnX() {
        return capturedPawnX;
    }

    public int getCapturedPawnY() {
        return capturedPawnY;
    }

    public boolean isCapturing() {
        return capturing;
    }
}
