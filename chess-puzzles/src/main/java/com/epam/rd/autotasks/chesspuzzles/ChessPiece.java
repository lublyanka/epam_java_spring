package com.epam.rd.autotasks.chesspuzzles;

public interface ChessPiece{
    enum Type {
        WHITE,
        BLACK
    }

    Cell getCell();

    char toChar();
}
