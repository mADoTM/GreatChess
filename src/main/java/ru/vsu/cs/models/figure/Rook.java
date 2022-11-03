package ru.vsu.cs.models.figure;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;

public class Rook extends AbstractFigure {
    public Rook(@NotNull Cell position, @NotNull PlayerType color) {
        super(position, color);
    }

    @Override
    public @NotNull AbstractFigure moveFigure(Cell cell) {
        return new Rook(cell, color);
    }

    @Override
    protected void updatePossibleSteps() {
        super.updatePossibleSteps();

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (j == position.x() || i == position.y()) {
                    possibleSteps.add(new Step(position, new Cell(i, j)));
                }
            }
        }
    }
}
