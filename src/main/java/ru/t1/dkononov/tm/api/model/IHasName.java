package ru.t1.dkononov.tm.api.model;

import org.jetbrains.annotations.NotNull;

public interface IHasName {

    @NotNull
    String getName();

    void setName(@NotNull final String name);

}
