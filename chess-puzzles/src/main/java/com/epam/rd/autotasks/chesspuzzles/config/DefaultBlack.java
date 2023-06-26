package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.BLACK;

@Configuration
public class DefaultBlack {


    @Bean
    public ChessPiece getKingBlack() {
        return new King(Cell.cell('E', 8), BLACK);
    }

    @Bean
    public ChessPiece getQueenBlack() {
        return new Queen(Cell.cell('D', 8), BLACK);
    }

    @Bean
    public ChessPiece getKnight1Black() {
        return new Knight(Cell.cell('B', 8), BLACK);
    }

    @Bean
    public ChessPiece getKnight2Black() {
        return new Knight(Cell.cell('G', 8), BLACK);
    }

    @Bean
    public ChessPiece getBishop1Black() {
        return new Bishop(Cell.cell('C', 8), BLACK);
    }

    @Bean
    public ChessPiece getBishop2Black() {
        return new Bishop(Cell.cell('F', 8), BLACK);
    }

    @Bean
    public ChessPiece getRook1Black() {
        return new Rook(Cell.cell('A', 8), BLACK);
    }

    @Bean
    public ChessPiece getRook2Black() {
        return new Rook(Cell.cell('H', 8), BLACK);
    }

    @Bean
    public ChessPiece getPawn1Black() {
        return new Pawn(Cell.cell('A', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn2Black() {
        return new Pawn(Cell.cell('B', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn3Black() {
        return new Pawn(Cell.cell('C', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn4Black() {
        return new Pawn(Cell.cell('D', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn5Black() {
        return new Pawn(Cell.cell('E', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn6Black() {
        return new Pawn(Cell.cell('F', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn7Black() {
        return new Pawn(Cell.cell('G', 7), BLACK);
    }

    @Bean
    public ChessPiece getPawn8Black() {
        return new Pawn(Cell.cell('H', 7), BLACK);
    }

}
