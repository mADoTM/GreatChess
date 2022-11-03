package ru.vsu.cs.models.figure;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFigure implements Figure {
    protected @NotNull Cell position;
    protected @NotNull List<Step> possibleSteps;
    protected final @NotNull PlayerType color;

    public AbstractFigure(@NotNull Cell position, @NotNull PlayerType color) {
        this.position = position;
        this.color = color;
        this.possibleSteps = new ArrayList<>();
        updatePossibleSteps();
    }

    @Override
    public @NotNull List<Step> getPossibleSteps() {
        return possibleSteps;
    }

    public @NotNull Cell getPosition() {
        return position;
    }

    public @NotNull PlayerType getColor() {
        return color;
    }

    public abstract @NotNull AbstractFigure moveFigure(Cell cell);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFigure that = (AbstractFigure) o;
        return position.equals(that.position) && possibleSteps.equals(that.possibleSteps) && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, possibleSteps, color);
    }

    protected void updatePossibleSteps() {
        possibleSteps = new ArrayList<>();
    }
}
