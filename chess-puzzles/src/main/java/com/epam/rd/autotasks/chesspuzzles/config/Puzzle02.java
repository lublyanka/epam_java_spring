package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.BLACK;
import static com.epam.rd.autotasks.chesspuzzles.ChessPiece.Type.WHITE;

@Configuration
public class Puzzle02 {
    @Bean
    public ChessPiece getQueenWhite() {
        return new Queen(Cell.cell('H', 8), WHITE);
    }

    @Bean
    public ChessPiece getQueenBlack() {
        return new Queen(Cell.cell('F', 4), BLACK);
    }

    @Bean
    public ChessPiece getKingWhite() {
        return new King(Cell.cell('B', 2), WHITE);
    }

    @Bean
    public ChessPiece getKingBlack() {
        return new King(Cell.cell('D', 3), BLACK);
    }

    @Bean
    public ChessPiece getPawn1Black() {
        return new Pawn(Cell.cell('E', 2), BLACK);
    }

}
