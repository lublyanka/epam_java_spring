package com.epam.rd.autotasks.chesspuzzles;

import org.springframework.beans.factory.annotation.Autowired;

public class King extends ChessPieceImpl{
    @Autowired
    public King(Cell cell, Type type) {
        super(cell, 'k', type);
    }
}
