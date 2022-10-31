package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.dto.model.UserDTO;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    @Nullable
    UserDTO create(@Nullable String login, @Nullable String password) throws AbstractException;

    @NotNull
    UserDTO create(@Nullable String login, @Nullable String password, @Nullable String email) throws AbstractException;

    @NotNull
    UserDTO create(@Nullable String login, @Nullable String password, @Nullable Role role) throws AbstractException;

    @Nullable
    UserDTO findByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable
    UserDTO findByEmail(@Nullable String email) throws EmailEmptyException;

    @Nullable
    UserDTO removeOne(@Nullable UserDTO model) throws UserIdEmptyException;

    @NotNull
    UserDTO removeByLogin(@Nullable String login) throws AbstractFieldException;

    @Nullable
    UserDTO removeByEmail(@Nullable String email) throws AbstractFieldException;

    @NotNull
    UserDTO setPassword(@Nullable String id, @Nullable String password) throws AbstractFieldException;

    @NotNull
    UserDTO updateUser(
            @Nullable String id,
            @Nullable String firstName,
            @Nullable String lastName,
            @Nullable String middleName
    ) throws AbstractFieldException;

    Boolean isLoginExist(@Nullable String login);

    Boolean isEmailExist(@Nullable String email);

    @Nullable UserDTO lockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable UserDTO unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable
    @SneakyThrows
    UserDTO findById(@Nullable String id)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    List<UserDTO> findAll();

    @NotNull
    @SneakyThrows
    Collection<UserDTO> set(@NotNull Collection<UserDTO> models);
}
