package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.dto.model.SessionDTO;

import java.util.Collection;
import java.util.List;

public interface ISessionService {
    @Nullable
    @SneakyThrows
    SessionDTO findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    List<SessionDTO> findAll();

    @Nullable
    @SneakyThrows
    SessionDTO add(@Nullable SessionDTO model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<SessionDTO> add(@NotNull Collection<SessionDTO> models);

    @SneakyThrows
    void clear(@Nullable String userId);

    @Nullable
    @SneakyThrows
    SessionDTO remove(@NotNull String userId, @Nullable SessionDTO model);

    @SneakyThrows
    boolean existsById(@Nullable String id);

    @NotNull List<SessionDTO> findAll(@Nullable String userId) throws UserIdEmptyException;
}
