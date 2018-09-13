package com.mercadolibre.desafio;

import java.awt.*;

class Block {

    Color upLeft;
    Color upRight;
    Color downLeft;
    Color downRight;

    Block(Color upLeft,
          Color upRight,
          Color downLeft,
          Color downRight) {
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.downLeft = downLeft;
        this.downRight = downRight;
    }

    boolean hasColor(Color color) {
        return color.equals(upLeft) || color.equals(upRight) || color.equals(downLeft) || color.equals(downRight);
    }
}
