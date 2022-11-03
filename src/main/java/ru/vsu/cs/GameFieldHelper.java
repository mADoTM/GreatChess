package ru.vsu.cs;

import ru.vsu.cs.models.Cell;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.figure.*;

public class GameFieldHelper {
    private static final int height = 10;
    private static final int width = 10;

    public static AbstractFigure[][] getStartFigures() {
        final var field = new AbstractFigure[height][width];

        field[0][0] = new Rook(new Cell(1, 1), PlayerType.WHITE);
        field[0][1] = new Knight(new Cell(2, 1), PlayerType.WHITE);
        field[0][2] = new Bishop(new Cell(3, 1), PlayerType.WHITE);
        field[0][3] = new Vizier(new Cell(4, 1), PlayerType.WHITE);
        field[0][4] = new Giraffe(new Cell(5, 1), PlayerType.WHITE);
        field[0][5] = new King(new Cell(6, 1), PlayerType.WHITE);
        field[0][6] = new General(new Cell(7, 1), PlayerType.WHITE);
        field[0][7] = new Bishop(new Cell(8, 1), PlayerType.WHITE);
        field[0][8] = new Knight(new Cell(9, 1), PlayerType.WHITE);
        field[0][9] = new Rook(new Cell(10, 1), PlayerType.WHITE);

        for (int i = 0; i < 10; i++) {
            field[1][i] = new Pawn(new Cell(i + 1, 2), PlayerType.WHITE);
            if (i == 4 || i == 5) {
                field[1][i] = new FightingMachine(new Cell(i + 1, 2), PlayerType.WHITE);
            }
        }
        field[2][4] = new Pawn(new Cell(5, 3), PlayerType.WHITE);
        field[2][5] = new Pawn(new Cell(6, 3), PlayerType.WHITE);


        field[9][0] = new Rook(new Cell(1, 10), PlayerType.BLACK);
        field[9][1] = new Knight(new Cell(2, 10), PlayerType.BLACK);
        field[9][2] = new Bishop(new Cell(3, 10), PlayerType.BLACK);
        field[9][3] = new General(new Cell(4, 10), PlayerType.BLACK);
        field[9][4] = new King(new Cell(5, 10), PlayerType.BLACK);
        field[9][5] = new Giraffe(new Cell(6, 10), PlayerType.BLACK);
        field[9][6] = new Vizier(new Cell(7, 10), PlayerType.BLACK);
        field[9][7] = new Bishop(new Cell(8, 10), PlayerType.BLACK);
        field[9][8] = new Knight(new Cell(9, 10), PlayerType.BLACK);
        field[9][9] = new Rook(new Cell(10, 10), PlayerType.BLACK);

        for (int i = 0; i < 10; i++) {
            field[8][i] = new Pawn(new Cell(i + 1, 9), PlayerType.BLACK);
            if (i == 4 || i == 5) {
                field[8][i] = new FightingMachine(new Cell(i + 1, 9), PlayerType.BLACK);
            }
        }
        field[7][4] = new Pawn(new Cell(5, 8), PlayerType.BLACK);
        field[7][5] = new Pawn(new Cell(6, 8), PlayerType.BLACK);

        return field;
    }

    public static boolean canCellBeInField(Cell cell) {
        return cell.x() <= width && cell.y() <= height && cell.x() > 0 && cell.y() > 0;
    }

    public static boolean isLowestCell(Cell cell) {
        return cell.y() == 1;
    }

    public static boolean isHighestCell(Cell cell) {
        return cell.y() == 10;
    }
}
