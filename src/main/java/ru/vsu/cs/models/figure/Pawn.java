package ru.vsu.cs.models.figure;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.GameFieldHelper;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;

public class Pawn extends AbstractFigure {

    public Pawn(@NotNull Cell position, PlayerType color) {
        super(position, color);
    }

    @Override
    public @NotNull AbstractFigure moveFigure(Cell cell) {
        final boolean isBlackGeneral = GameFieldHelper.isLowestCell(cell) && color == PlayerType.BLACK;
        final boolean isWhiteGeneral = GameFieldHelper.isHighestCell(cell) && color == PlayerType.WHITE;

        if (isWhiteGeneral || isBlackGeneral) {
            return new General(cell, color);
        }

        return new Pawn(cell, color);
    }

    @Override
    protected void updatePossibleSteps() {
        super.updatePossibleSteps();
        for (int i = -1; i <= 1; i++) {
            Cell cell;

            if (color == PlayerType.WHITE) {
                cell = new Cell(position.x() + i, position.y() + 1);
            } else {
                cell = new Cell(position.x() + i, position.y() - 1);
            }

            if (GameFieldHelper.canCellBeInField(cell)) {
                possibleSteps.add(new Step(position, cell));
            }
        }
    }

}
