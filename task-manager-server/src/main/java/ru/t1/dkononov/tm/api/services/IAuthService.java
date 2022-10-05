package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.User;

public interface IAuthService {

    @Nullable
    User registry(@NotNull String login, @NotNull String password, @NotNull String email)
            throws AbstractException;


    boolean isAuth();

    @Nullable
    String getUserId() throws AccessDeniedException;

    @NotNull
    User getUser() throws AbstractFieldException;


    @NotNull
    User check(String login, String password) throws AbstractException;

}
