package ru.vsu.cs;

public class ChessFieldFactory {
    public static ChessField create() {
        return new ChessField(GameFieldHelper.getStartFigures());
    }
}
