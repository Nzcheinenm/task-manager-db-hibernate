package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface IRepository<M extends AbstractModel> {

    @NotNull
    List<M> findAll();

    @Nullable
    List<M> findAll(@Nullable Comparator<M> comparator);

    @Nullable
    M add(@NotNull M m) throws ProjectNotFoundException;

    @NotNull
    Collection<M> add(@NotNull Collection<M> models);

    @NotNull
    Collection<M> set(@NotNull Collection<M> models);

    void clear();

    boolean existsById(@Nullable String id);

    @Nullable
    M findById(@NotNull String id) throws IdEmptyException;

    @Nullable
    M findByIndex(@NotNull Integer index) throws IndexIncorrectException;

    @Nullable
    M remove(@NotNull M m);

    @Nullable
    M removeById(@NotNull String id) throws IdEmptyException;

    @Nullable
    M removeByIndex(@NotNull Integer index) throws IndexIncorrectException;

    void removeAll(@Nullable List<M> modelsRemove);
}
