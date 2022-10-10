package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;

public interface IConnectionProvider {

    @NotNull
    String getPort();

    @NotNull
    String getHost();

}
