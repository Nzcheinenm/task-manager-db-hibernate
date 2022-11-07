package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.SessionDTO;

import java.util.List;

public interface ISessionDTORepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<SessionDTO> findAll();

    @NotNull List<SessionDTO> findAll(@NotNull String userId);

    @NotNull SessionDTO findById(@NotNull String id);

    @Nullable SessionDTO findById(@NotNull String userId, @NotNull String id);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);
}
