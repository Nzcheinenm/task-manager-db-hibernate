package ru.t1.dkononov.tm.api.services.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IProjectDTORepository;
import ru.t1.dkononov.tm.api.repository.dto.ITaskDTORepository;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;

import java.util.List;

public interface IUserDTOService extends IDTOService<UserDTO> {
    @NotNull ITaskDTORepository getTaskRepository();

    @NotNull IProjectDTORepository getProjectRepository();

    @Nullable
    @SneakyThrows
    UserDTO create(
            @Nullable String login,
            @Nullable String password
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    UserDTO create(
            @Nullable String login,
            @Nullable String password,
            @Nullable String email
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    UserDTO create(
            @Nullable String login,
            @Nullable String password,
            @Nullable Role role
    ) throws AbstractException;

    @Nullable
    @SneakyThrows
    UserDTO findByLogin(@Nullable String login);

    @Nullable
    @SneakyThrows
    UserDTO findByEmail(@Nullable String email) throws EmailEmptyException;

    @Nullable
    @SneakyThrows
    UserDTO removeOne(@Nullable UserDTO model) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    UserDTO removeByLogin(@Nullable String login) throws AbstractFieldException;

    @NotNull
    @SneakyThrows
    UserDTO removeByEmail(@Nullable String email) throws AbstractFieldException;

    @NotNull
    @SneakyThrows
    UserDTO setPassword(
            @Nullable String id,
            @Nullable String password
    ) throws AbstractFieldException;

    @NotNull
    @SneakyThrows
    UserDTO updateUser(
            @Nullable String id,
            @Nullable String firstName,
            @Nullable String lastName,
            @Nullable String middleName
    ) throws AbstractFieldException;

    @Nullable
    @SneakyThrows
    Boolean isLoginExist(@Nullable String login);

    @Nullable
    @SneakyThrows
    Boolean isEmailExist(@Nullable String email);

    @Nullable
    @SneakyThrows
    UserDTO lockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable
    @SneakyThrows
    UserDTO unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException;

    @Nullable List<UserDTO> findAll(@Nullable Sort sort);
}
