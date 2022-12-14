package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.User;

import java.util.List;

public interface IUserRepository {
    void clear();

    @NotNull List<User> findAll();

    @NotNull List<User> findAll(@NotNull Sort sort);

    @NotNull User findById(@NotNull String id);

    @NotNull User findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    @Nullable User findByLogin(@NotNull String login);

    @Nullable User findByEmail(@NotNull String email);
}
