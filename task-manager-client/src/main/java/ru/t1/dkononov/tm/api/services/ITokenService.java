package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ITokenService {

    @Nullable
    String getToken();

    void setToken(@NotNull String token);

}
