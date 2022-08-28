package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.component.ISaltProvider;

public interface IPropertyService extends ISaltProvider {
    @NotNull String getApplicationVersion();

    @NotNull String getAuthorEmail();

    @NotNull String getAuthorName();
}
