package com.mercadolibre.desafio;

import java.awt.*;
import java.awt.image.BufferedImage;

class Block {

    Color upLeft;
    Color upRight;
    Color downLeft;
    Color downRight;

    BufferedImage image;

    Block(BufferedImage image,
          Color upLeft,
          Color upRight,
          Color downLeft,
          Color downRight) {
        this.image = image;
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.downLeft = downLeft;
        this.downRight = downRight;
    }

    boolean hasColor(Color color) {
        return color.equals(upLeft) || color.equals(upRight) || color.equals(downLeft) || color.equals(downRight);
    }

    boolean isUpperLeft() {
        return isBlackOrWhite(upLeft) &&
                isBlackOrWhite(downLeft) &&
                        isBlackOrWhite(upRight);
    }

    boolean isUpperRight() {
        return isBlackOrWhite(upLeft) &&
                isBlackOrWhite(upRight) &&
                        isBlackOrWhite(downRight);
    }

    boolean isDownLeft() {
        return isBlackOrWhite(upLeft) &&
                isBlackOrWhite(downLeft) &&
                        isBlackOrWhite(downRight);
    }

    boolean isDownRight() {
        return isBlackOrWhite(upRight) &&
                isBlackOrWhite(downRight) &&
                isBlackOrWhite(downLeft);
    }

    boolean isTopBorder() { // Arriba negro o blanco. Abajo color
        return isBlackOrWhite(upLeft) && isBlackOrWhite(upRight)
                && !isBlackOrWhite(downLeft) && !isBlackOrWhite(downRight);
    }

    boolean isBottomBorder() { // Arriba color. Abajo negro o blanco.
        return !isBlackOrWhite(upLeft) && !isBlackOrWhite(upRight)
                && isBlackOrWhite(downLeft) && isBlackOrWhite(downRight);
    }

    boolean isLeftBorder() { // Izquierda negro o blanco. Derecha color.
        return isBlackOrWhite(upLeft) && !isBlackOrWhite(upRight)
                && isBlackOrWhite(downLeft) && !isBlackOrWhite(downRight);
    }

    boolean isRightBorder() { // Izquierda color. Derecha negro o blanco.
        return !isBlackOrWhite(upLeft) && isBlackOrWhite(upRight)
                && !isBlackOrWhite(downLeft) && isBlackOrWhite(downRight);
    }

    private boolean isBlackOrWhite(Color color) {
        return color.equals(Color.BLACK) || color.equals(Color.WHITE);
    }
}
