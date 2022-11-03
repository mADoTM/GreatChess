package ru.vsu.cs;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.models.Step;

class Converter {
    public static Step stringCommandToStep(@NotNull String command) {
        if (command.split(" ").length < 2) {
            throw new IllegalArgumentException("Неверно введенная команда");
        }

        final var leftStr = command.split(" ")[0];
        final var rightStr = command.split(" ")[1];

        final var leftCell = stringToCell(leftStr);
        final var rightCell = stringToCell(rightStr);

        if (!GameFieldHelper.canCellBeInField(leftCell) || !GameFieldHelper.canCellBeInField(rightCell)) {
            throw new IllegalArgumentException("Неверно введенные координаты");
        }

        return new Step(leftCell, rightCell);
    }

    public static Cell stringToCell(String str) {
        if (str.length() > 3 || str.length() < 1) {
            throw new IllegalArgumentException("Неверно введенные координаты фигуры");
        }
        str = str.toLowerCase();

        int x = str.charAt(0) - 96;
        int y = Integer.parseInt(str.substring(1));

        Cell cell = new Cell(x, y);

        if (!GameFieldHelper.canCellBeInField(cell)) {
            throw new IllegalArgumentException("Неверно введенные координаты точки: " + x + " " + y);
        }

        return cell;
    }
}
