package ru.t1.dkononov.tm.api.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IHasCreated {

    @NotNull
    Date getCreated();

    void setCreated(@NotNull final Date date);

}
