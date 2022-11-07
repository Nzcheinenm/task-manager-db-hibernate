package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.List;

public interface IAbstractRepository<E extends AbstractModel> {
    void add(E entity);

    void clear();

    @NotNull List<E> findAll();

    E findById(@NotNull String id);

    void remove(E entity);

    void update(E entity);
}
