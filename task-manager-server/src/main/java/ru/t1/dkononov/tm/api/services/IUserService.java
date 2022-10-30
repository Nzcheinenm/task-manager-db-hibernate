package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    @Nullable
    User create(@Nullable String login, @Nullable String password) throws AbstractException;

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable String email) throws AbstractException;

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable Role role) throws AbstractException;

    @Nullable
    User findByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable
    User findByEmail(@Nullable String email) throws EmailEmptyException;

    @Nullable
    User removeOne(@Nullable User model) throws UserIdEmptyException;

    @NotNull
    User removeByLogin(@Nullable String login) throws AbstractFieldException;

    @Nullable
    User removeByEmail(@Nullable String email) throws AbstractFieldException;

    @NotNull
    User setPassword(@Nullable String id, @Nullable String password) throws AbstractFieldException;

    @NotNull
    User updateUser(
            @Nullable String id,
            @Nullable String firstName,
            @Nullable String lastName,
            @Nullable String middleName
    ) throws AbstractFieldException;

    Boolean isLoginExist(@Nullable String login);

    Boolean isEmailExist(@Nullable String email);

    @Nullable User lockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable User unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable
    @SneakyThrows
    User findById(@Nullable String id)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    List<User> findAll();

    @NotNull
    @SneakyThrows
    Collection<User> set(@NotNull Collection<User> models);
}
