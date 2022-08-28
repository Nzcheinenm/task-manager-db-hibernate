package ru.t1.dkononov.tm.api.component;

import org.jetbrains.annotations.NotNull;

public interface ISaltProvider {

    @NotNull Integer getPasswordIteration();

    @NotNull String getPasswordSecret();

}
