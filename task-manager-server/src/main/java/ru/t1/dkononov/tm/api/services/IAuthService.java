package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.LoginEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.User;

public interface IAuthService {

    @NotNull
    @SneakyThrows
    Session validateToken(@Nullable String token);

    void invalidate(@Nullable Session session) throws UserIdEmptyException;

    @NotNull
    String login(
            @Nullable String login,
            @Nullable String password
    ) throws LoginEmptyException, Exception;

    @Nullable
    User registry(@NotNull String login, @NotNull String password, @NotNull String email)
            throws AbstractException;


    boolean isAuth();

    @Nullable
    String getUserId() throws AccessDeniedException;

    @NotNull
    User getUser() throws AbstractException;


    @NotNull
    User check(String login, String password) throws AbstractException;

}
