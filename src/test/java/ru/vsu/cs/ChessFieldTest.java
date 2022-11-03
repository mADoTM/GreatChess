package ru.vsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessFieldTest {
    private ChessField field;

    @Test
    void fieldFiguresHasSameOrderedFiguresLikeFromGameFieldHelper() {
        final var helperFigures = GameFieldHelper.getStartFigures();
        final var fieldFigures = field.getField();


        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(helperFigures[i][j], fieldFigures[i][j]);
            }
        }
    }

    @Test
    void throwExceptionWhenTryToMoveFigureOnIllegalPoint() {
        assertThrows(IllegalArgumentException.class, () -> field.executeCommand("a0 b1410"));
        assertThrows(IllegalArgumentException.class, () -> field.executeCommand("a0 b1"));
        assertThrows(IllegalArgumentException.class, () -> field.executeCommand("a2"));
    }

    @Test
    void throwExceptionWhenMoveUnexistedPoint() {
        assertThrows(IllegalArgumentException.class, () -> field.executeCommand("a3 a4"));
    }

    @Test
    void throwExceptionWhenMovePawnLeftAndRightSideIfThatFree() {
        assertThrows(IllegalStateException.class, () -> field.executeCommand("a2 b3"));
        assertThrows(IllegalStateException.class, () -> field.executeCommand("a2 b1"));
    }

    @Test
    void doesNotThrowExceptionWhenMovePawnStraight() {
        assertDoesNotThrow(() -> field.executeCommand("a2 a3"));
    }

    @Test
    void throwExceptionWhenTryToMoveSameColorFigures() {
        field.executeCommand("a2 a3");
        assertThrows(IllegalStateException.class, () -> field.executeCommand("a3 a4"));
    }

    @Test
    void throwExceptionWhenGameEnded() {
        // e1 a5 -> j9 j8 -> a5 d8 -> j8 j7 -> d8 e10 -> j7 j6
        field.executeCommand("e1 a5");
        field.executeCommand("j9 j8");
        field.executeCommand("a5 d8");
        field.executeCommand("j8 j7");
        field.executeCommand("d8 e10");
        assertThrows(IllegalStateException.class, () -> field.executeCommand("j7 j6"));
    }

    @BeforeEach
    public void setDefaults() {
         field = ChessFieldFactory.create();
    }
}