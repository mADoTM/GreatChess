package ru.vsu.cs.models.figure;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.GameFieldHelper;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;

import java.util.ArrayList;
import java.util.List;

public class FightingMachine extends AbstractFigure {
    public FightingMachine(@NotNull Cell position, @NotNull PlayerType color) {
        super(position, color);
    }

    @Override
    public @NotNull AbstractFigure moveFigure(Cell cell) {
        return new FightingMachine(cell, color);
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

        final List<Cell> potentialCells = new ArrayList<>();

        potentialCells.add(new Cell(position.x() - 1, position.y() - 2));
        potentialCells.add(new Cell(position.x() + 1, position.y() - 2));
        potentialCells.add(new Cell(position.x() + 2, position.y() - 1));
        potentialCells.add(new Cell(position.x() + 2, position.y() + 1));
        potentialCells.add(new Cell(position.x() - 1, position.y() + 2));
        potentialCells.add(new Cell(position.x() + 1, position.y() + 2));
        potentialCells.add(new Cell(position.x() - 2, position.y() - 1));
        potentialCells.add(new Cell(position.x() - 2, position.y() + 1));

        for (var cell : potentialCells) {
            if (GameFieldHelper.canCellBeInField(cell)) {
                possibleSteps.add(new Step(position, cell));
            }
        }
    }
}
