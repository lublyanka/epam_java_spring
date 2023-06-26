package com.epam.rd.autotasks.chesspuzzles;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChessBoardImpl implements ChessBoard {
    public final Collection<ChessPiece> pieces;

    public ChessBoardImpl(Collection<ChessPiece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String state() {
        StringBuilder sb = new StringBuilder();
        Map<Cell,ChessPiece> chessPieceMap = new HashMap<>();
        pieces.forEach(x -> chessPieceMap.put(x.getCell(), x));

        for (int number = 8; number >= 1; number--) {
            for (char letter = 'A'; letter <= 'H'; letter++) {
                //appendPiece(sb, chessPieceMap, number, letter);
                appendPiece(sb, chessPieceMap, letter, number);
            }
            sb.append("\n");
        }
        return sb.toString().strip();
    }

    /*private void appendPiece(StringBuilder sb, int number, char letter) {
        Cell currCell = Cell.cell(letter, number);

        Optional<ChessPiece> piece = pieces
                .parallelStream()
                .filter(x -> x.getCell() == currCell).findFirst();
        if (piece.isPresent())
            sb.append(piece.get().toChar());
        else
            sb.append('.');
    }*/

    private void appendPiece(StringBuilder sb, Map<Cell,ChessPiece> chessPieceMap,  char letter, int number) {
        Cell currCell = Cell.cell(letter, number);

        ChessPiece piece = chessPieceMap.get(currCell);
        if (piece!=null)
            sb.append(piece.toChar());
        else
            sb.append('.');
    }

}
