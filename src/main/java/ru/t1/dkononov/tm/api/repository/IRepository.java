package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Comparator;
import java.util.List;

public interface IRepository<M extends AbstractModel> {

    @NotNull
    List<M> findAll();

    @Nullable
    List<M> findAll(@Nullable final Comparator<M> comparator);

    @Nullable
    M add(@NotNull final M m) throws ProjectNotFoundException;

    void clear();

    boolean existsById(@Nullable final String id);

    @Nullable
    M findById(@NotNull final String id) throws IdEmptyException;

    @Nullable
    M findByIndex(final Integer index) throws IndexIncorrectException;

    @Nullable
    M remove(final M m);

    @Nullable
    M removeById(@NotNull final String id) throws IdEmptyException;

    @Nullable
    M removeByIndex(@NotNull final Integer index) throws IndexIncorrectException;

    void removeAll(@Nullable List<M> modelsRemove);
}
