package ru.vsu.cs;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.models.Cell;
import ru.vsu.cs.models.PlayerType;
import ru.vsu.cs.models.Step;
import ru.vsu.cs.models.figure.AbstractFigure;
import ru.vsu.cs.models.figure.King;
import ru.vsu.cs.models.figure.Pawn;

public class ChessField {
    private PlayerType player;

    private final AbstractFigure[][] field;

    private boolean gameEnded;

    public ChessField(AbstractFigure[][] figures) {
        this.player = PlayerType.WHITE;
        this.field = figures;
        this.gameEnded = false;
    }

    public AbstractFigure[][] getField() {
        final var copy = new AbstractFigure[10][10];

        for (int i = 0; i < 10; i++) {
            System.arraycopy(field[i], 0, copy[i], 0, 10);
        }

        return copy;
    }

    public void executeCommand(String command) {
        if(gameEnded) {
            throw new IllegalStateException("игра закончена");
        }

        final var step = Converter.stringCommandToStep(command);
        moveFigure(step);
    }

    private void moveFigure(@NotNull Step step) {
        final var attackerFigure = getFigure(step.start());

        if(attackerFigure.getColor() != player) {
            throw new IllegalStateException("Нельзя ходить чужой фигурой");
        }

        if(attackerFigure instanceof Pawn) {
            if(isCellBusy(step.end())) {
                if(getFigure(step.end()).getPosition().y() == attackerFigure.getPosition().y()) {
                    throw new IllegalStateException("Пешка не может походить сюда");
                }
            } else {
                if(step.end().x() != attackerFigure.getPosition().x()) {
                    throw new IllegalStateException("Пешка не может походить сюда");
                }
            }
        }

        if (isCellBusy(step.end())) {
            if (getFigure(step.end()).getColor() == attackerFigure.getColor()) {
                throw new IllegalStateException("Конечная точка занята фигурой того же цвета");
            } else if (getFigure(step.end()) instanceof King) {
                gameEnded = true;
            }
        }

        if (attackerFigure.getPossibleSteps().stream().anyMatch(x -> x.end().equals(step.end()))) {
            field[step.start().y() - 1][step.start().x() - 1] = null;
            field[step.end().y() - 1][step.end().x() - 1] = attackerFigure.moveFigure(step.end());
        }

        switchPlayer();
    }

    private @NotNull AbstractFigure getFigure(Cell cell) {
        if (!GameFieldHelper.canCellBeInField(cell) || !isCellBusy(cell)) {
            throw new IllegalArgumentException(cell.toString());
        }
        return field[cell.y() - 1][cell.x() - 1];
    }

    private void switchPlayer() {
        player = player == PlayerType.WHITE ? PlayerType.BLACK : PlayerType.WHITE;
    }

    public boolean isCellBusy(Cell cell) {
        return field[cell.y() - 1][cell.x() - 1] != null;
    }
}
