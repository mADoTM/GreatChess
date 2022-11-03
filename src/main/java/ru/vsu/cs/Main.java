package ru.vsu.cs;

import ru.vsu.cs.models.figure.AbstractFigure;

public class Main {
    public static void main(String[] args) {
        final var chess = ChessFieldFactory.create();
        chess.executeCommand("e1 a5");
        chess.executeCommand("j9 j8");
        chess.executeCommand("a5 d8");
        chess.executeCommand("j8 j7");
        chess.executeCommand("d8 e10");
        chess.executeCommand("j7 j6");
        final var field = chess.getField();
        printField(field);
    }

    private static void printField(AbstractFigure[][] field) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] != null) {
                    final var details = field[i][j].getClass().getName().split("\\.");
                    System.out.print(details[details.length - 1] + "|");
                } else {
                    System.out.print("\t|");
                }
            }
            System.out.print("\n");
        }
    }
}