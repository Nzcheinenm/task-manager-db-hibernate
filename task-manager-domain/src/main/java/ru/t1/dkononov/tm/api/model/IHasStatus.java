package ru.t1.dkononov.tm.api.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.enumerated.Status;

public interface IHasStatus {

    @NotNull
    Status getStatus();

    void setStatus(@NotNull Status status);

}
