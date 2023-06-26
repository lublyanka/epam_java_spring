package com.epam.rd.autotasks.chesspuzzles;

public class ChessPieceImpl implements ChessPiece{
    Cell cell;
    char piece;
    Type type;

    public ChessPieceImpl(Cell cell, char piece, Type type) {
        this.cell = cell;
        this.piece = type == Type.WHITE ? Character.toLowerCase(piece) : Character.toUpperCase(piece);
        this.type = type;
    }

    @Override
    public Cell getCell() {
        return cell;
    }
    @Override
    public char toChar(){
        return piece;
    }
}
