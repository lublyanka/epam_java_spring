package com.epam.rd.autotasks.chesspuzzles;

import org.springframework.stereotype.Component;

public interface ChessPiece {
    @Component
    enum Type {
        WHITE,
        BLACK
    }
    Cell getCell();
    char toChar();
}
