package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.BLACK;
import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.WHITE;

@Configuration
public class Puzzle01 {
    @Bean
    public ChessPiece getRook1Black() {
        return new Rook(Cell.cell('E', 8), BLACK);
    }

    @Bean
    public ChessPiece getKingWhite() {
        return new King(Cell.cell('B', 7), WHITE);
    }

    @Bean
    public ChessPiece getKnight1White() {
        return new Knight(Cell.cell('C', 6), WHITE);
    }

    @Bean
    public ChessPiece getBishop1Black() {
        return new Bishop(Cell.cell('D', 6), BLACK);
    }

    @Bean
    public ChessPiece getPawn1Black() {
        return new Pawn(Cell.cell('F', 6), BLACK);
    }

    @Bean
    public ChessPiece getKingBlack() {
        return new King(Cell.cell('C', 5), BLACK);
    }

    @Bean
    public ChessPiece getBishop2Black() {
        return new Bishop(Cell.cell('D', 5), BLACK);
    }

    @Bean
    public ChessPiece getPawn2Black() {
        return new Pawn(Cell.cell('C', 3), BLACK);
    }

    @Bean
    public ChessPiece getPawn1White() {
        return new Pawn(Cell.cell('D', 2), WHITE);
    }

    @Bean
    public ChessPiece getBishop1White() {return new Bishop(Cell.cell('E', 2), WHITE);}

}
