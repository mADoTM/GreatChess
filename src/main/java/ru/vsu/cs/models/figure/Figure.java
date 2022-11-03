package ru.vsu.cs.models.figure;

import ru.vsu.cs.models.Step;

import java.util.List;

public interface Figure {
    List<Step> getPossibleSteps();
}
