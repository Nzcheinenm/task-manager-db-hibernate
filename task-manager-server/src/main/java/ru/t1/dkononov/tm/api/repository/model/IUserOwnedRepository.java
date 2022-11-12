package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.List;

public interface IUserOwnedRepository<E extends AbstractUserOwnedModel> extends IRepository<E> {
    void add(@NotNull String userId, E entity);

    void remove(@NotNull String userId, E entity);

    void update(@NotNull String userId, E entity);

    void clear(@NotNull String userId);

    @Nullable
    E findById(@NotNull String userId, @NotNull String id);

    @Nullable
    List<E> findAll(@NotNull String userId);

    @Nullable
    E findByIndex(@NotNull String userId, @NotNull Integer index);

}
