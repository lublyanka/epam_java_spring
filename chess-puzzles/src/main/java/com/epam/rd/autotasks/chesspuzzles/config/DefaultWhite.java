package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.WHITE;

@Configuration
public class DefaultWhite {

    @Bean
    public ChessPiece getKingWhite() {
        return new King(Cell.cell('E', 1), WHITE);
    }

    @Bean
    public ChessPiece getQueenWhite() {
        return new Queen(Cell.cell('D', 1), WHITE);
    }

    @Bean
    public ChessPiece getKnight1White() {
        return new Knight(Cell.cell('B', 1), WHITE);
    }

    @Bean
    public ChessPiece getKnight2White() {
        return new Knight(Cell.cell('G', 1), WHITE);
    }

    @Bean
    public ChessPiece getBishop1White() {
        return new Bishop(Cell.cell('C', 1), WHITE);
    }

    @Bean
    public ChessPiece getBishop2White() {
        return new Bishop(Cell.cell('F', 1), WHITE);
    }

    @Bean
    public ChessPiece getRook1White() {
        return new Rook(Cell.cell('A', 1), WHITE);
    }

    @Bean
    public ChessPiece getRook2White() {
        return new Rook(Cell.cell('H', 1), WHITE);
    }

    @Bean
    public ChessPiece getPawn1White() {
        return new Pawn(Cell.cell('A', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn2White() {
        return new Pawn(Cell.cell('B', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn3White() {
        return new Pawn(Cell.cell('C', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn4White() {
        return new Pawn(Cell.cell('D', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn5White() {
        return new Pawn(Cell.cell('E', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn6White() {
        return new Pawn(Cell.cell('F', 2), WHITE);
    }

    @Bean
    public ChessPiece getPawn7White() { return new Pawn(Cell.cell('G', 2), WHITE); }

    @Bean
    public ChessPiece getPawn8White() { return new Pawn(Cell.cell('H', 2), WHITE); }

}
