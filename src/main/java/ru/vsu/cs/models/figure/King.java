package ru.vsu.cs.models.figure;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.GameFieldHelper;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractFigure {
    public King(@NotNull Cell position, @NotNull PlayerType color) {
        super(position, color);
    }

    @Override
    public @NotNull AbstractFigure moveFigure(Cell cell) {
        return new King(cell, color);
    }

    @Override
    protected void updatePossibleSteps() {
        super.updatePossibleSteps();

        final List<Cell> potentialCells = new ArrayList<>();

        potentialCells.add(new Cell(position.x() - 1, position.y() - 1));
        potentialCells.add(new Cell(position.x() - 1, position.y()));
        potentialCells.add(new Cell(position.x() - 1, position.y() + 1));
        potentialCells.add(new Cell(position.x(), position.y() - 1));
        potentialCells.add(new Cell(position.x(), position.y() + 1));
        potentialCells.add(new Cell(position.x() + 1, position.y() - 1));
        potentialCells.add(new Cell(position.x() + 1, position.y()));
        potentialCells.add(new Cell(position.x() + 1, position.y() + 1));

        for (var cell : potentialCells) {
            if (GameFieldHelper.canCellBeInField(cell)) {
                possibleSteps.add(new Step(position, cell));
            }
        }
    }
}
