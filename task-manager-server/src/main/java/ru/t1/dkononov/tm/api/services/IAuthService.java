package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.LoginEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

public interface IAuthService {

    @NotNull
    @SneakyThrows
    SessionDTO validateToken(@Nullable String token);

    void invalidate(@Nullable SessionDTO session) throws UserIdEmptyException;

    @NotNull
    String login(
            @Nullable String login,
            @Nullable String password
    ) throws LoginEmptyException, Exception;

    @Nullable
    UserDTO registry(@NotNull String login, @NotNull String password, @NotNull String email)
            throws AbstractException;


    boolean isAuth();

    @Nullable
    String getUserId() throws AccessDeniedException;

    @NotNull
    UserDTO getUser() throws AbstractException;


    @NotNull
    UserDTO check(String login, String password) throws AbstractException;

}
