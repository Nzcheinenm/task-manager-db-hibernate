package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Collection;
import java.util.List;

public interface IService<M extends AbstractModel> {

    @Nullable
    List<M> findAll(@Nullable Sort sort);


    @SneakyThrows
    void clear();

    @SneakyThrows
    boolean existsById(@Nullable String id);

    @NotNull
    @SneakyThrows
    List<M> findAll();

    @SneakyThrows
    M add(M model);

    @Nullable
    @SneakyThrows
    Collection<M> add(@NotNull Collection<M> models);

    @NotNull
    @SneakyThrows
    Collection<M> set(@NotNull Collection<M> models);

    @SneakyThrows
    M remove(M model) throws UserIdEmptyException;

    @SneakyThrows
    M findById(@Nullable String id);

    @SneakyThrows
    M findByIndex(@Nullable Integer index)
            throws AbstractException;

    @SneakyThrows
    M removeById(@Nullable String id) throws AbstractException;

    @SneakyThrows
    M removeByIndex(@Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll() throws UserIdEmptyException;
}
