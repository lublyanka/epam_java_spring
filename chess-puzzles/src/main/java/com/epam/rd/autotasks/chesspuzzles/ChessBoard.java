package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;

public interface ChessBoard {

    static ChessBoard of(Collection<ChessPiece> pieces){
        return new ChessBoardImpl(pieces); //WTF why are we depending on concrete class
    }

    String state();
}
