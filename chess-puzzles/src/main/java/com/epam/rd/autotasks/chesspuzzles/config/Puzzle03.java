package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.BLACK;
import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.WHITE;

@Configuration
public class Puzzle03 {
    @Bean
    public ChessPiece getRook1White() {
        return new Rook(Cell.cell('H', 8), WHITE);
    }

    @Bean
    public ChessPiece getRook2White() {
        return new Rook(Cell.cell('F', 7), WHITE);
    }

    @Bean
    public ChessPiece getQueenBlack() {
        return new Queen(Cell.cell('D', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn1Black() {
        return new Pawn(Cell.cell('E', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn2Black() {
        return new Pawn(Cell.cell('B', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn3Black() {
        return new Pawn(Cell.cell('A', 6), BLACK);
    }

    @Bean
    public ChessPiece getPawn4Black() {
        return new Pawn(Cell.cell('D', 6), BLACK);
    }

    @Bean
    public ChessPiece getPawn5Black() {
        return new Pawn(Cell.cell('G', 6), BLACK);
    }

    @Bean
    public ChessPiece getKingBlack() {
        return new King(Cell.cell('G', 5), BLACK);
    }

    @Bean
    public ChessPiece getKingWhite() {
        return new King(Cell.cell('H', 1), WHITE);
    }

    @Bean
    public ChessPiece getPawn1White() {
        return new Pawn(Cell.cell('D', 5), WHITE);
    }

    @Bean
    public ChessPiece getPawn2White() {
        return new Pawn(Cell.cell('G', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn3White() {
        return new Pawn(Cell.cell('H', 2), WHITE);
    }

    @Bean
    public ChessPiece getBishop1Black() {
        return new Bishop(Cell.cell('B', 4), BLACK);
    }

    @Bean
    public ChessPiece getRook1Black() {
        return new Rook(Cell.cell('C', 3), BLACK);
    }

    @Bean
    public ChessPiece getRook2Black() {
        return new Rook(Cell.cell('C', 1), BLACK);
    }

    @Bean
    public ChessPiece getBishop1White() {
        return new Bishop(Cell.cell('D', 1), WHITE);
    }

}
