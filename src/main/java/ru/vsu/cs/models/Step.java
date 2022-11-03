package ru.vsu.cs.models;

import org.jetbrains.annotations.NotNull;

public record Step(@NotNull Cell start, @NotNull Cell end) {
}
