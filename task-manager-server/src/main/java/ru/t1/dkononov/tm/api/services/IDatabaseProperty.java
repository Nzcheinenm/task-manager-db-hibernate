package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;

public interface IDatabaseProperty {

    @NotNull
    String getDatabaseUser();

    @NotNull
    String getDatabasePassword();

    @NotNull
    String getDatabaseUrl();

}
