package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

public interface IAbstractUserOwnedRepository<E extends AbstractUserOwnedModel> {
    void add(@NotNull String userId, E entity);

    void remove(@NotNull String userId, E entity);

    void update(@NotNull String userId, E entity);
}
