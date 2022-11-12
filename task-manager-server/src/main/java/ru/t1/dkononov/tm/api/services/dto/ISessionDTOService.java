package ru.t1.dkononov.tm.api.services.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import java.util.List;

public interface ISessionDTOService extends IUserOwnedDTOService<SessionDTO> {
    @NotNull
    @SneakyThrows
    List<SessionDTO> findAll();

    @Nullable
    @SneakyThrows
    SessionDTO add(@Nullable SessionDTO model);

    @SneakyThrows
    void clear(@Nullable String userId);

    @Nullable
    @SneakyThrows
    SessionDTO remove(@NotNull String userId, @Nullable SessionDTO model);

    @Nullable List<SessionDTO> findAll(@Nullable Sort sort);

    @SneakyThrows
    boolean existsById(@Nullable String id);
}
