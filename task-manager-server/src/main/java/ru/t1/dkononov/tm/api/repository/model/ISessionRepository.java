package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Session;

import java.util.List;

public interface ISessionRepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<Session> findAll();

    @NotNull List<Session> findAll(@NotNull String userId);

    @NotNull Session findById(@NotNull String id);

    @Nullable Session findById(@NotNull String userId, @NotNull String id);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);
}
