package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Session;

import java.util.Collection;
import java.util.List;

public interface ISessionService {
    @Nullable
    @SneakyThrows
    Session findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    List<Session> findAll();

    @Nullable
    @SneakyThrows
    Session add(@Nullable Session model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<Session> add(@NotNull Collection<Session> models);

    @SneakyThrows
    void clear(@Nullable String userId);

    @Nullable
    @SneakyThrows
    Session remove(@NotNull String userId, @Nullable Session model);

    @SneakyThrows
    boolean existsById(@Nullable String id);

    @NotNull List<Session> findAll(@Nullable String userId) throws UserIdEmptyException;
}
