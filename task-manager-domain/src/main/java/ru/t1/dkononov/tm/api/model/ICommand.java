package ru.t1.dkononov.tm.api.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ICommand {

    @Nullable
    String getARGUMENT();

    @NotNull
    String getDESCRIPTION();

    @NotNull
    String getNAME();

    void execute() throws Exception;

}
