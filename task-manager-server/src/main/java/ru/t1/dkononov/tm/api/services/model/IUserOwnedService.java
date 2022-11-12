package ru.t1.dkononov.tm.api.services.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public interface IUserOwnedService<M extends AbstractUserOwnedModel> {
    @SneakyThrows
    void clear(@Nullable String userId) throws UserIdEmptyException;

    @SneakyThrows
    boolean existsById(@Nullable String userId, @Nullable String id) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<M> findAll(@Nullable String userId) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    List<M> findAll(
            @Nullable String userId,
            @Nullable Comparator comparator
    ) throws UserIdEmptyException;

    @SneakyThrows
    M add(@Nullable String userId, M model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @SneakyThrows
    M remove(@NotNull String userId, M model) throws UserIdEmptyException;

    @SneakyThrows
    M findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @SneakyThrows
    M findByIndex(@Nullable String userId, @Nullable Integer index)
            throws AbstractException;

    @SneakyThrows
    M removeById(@Nullable String userId, @Nullable String id) throws AbstractException;

    @SneakyThrows
    M removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull List<M> findAll(@Nullable final String userId, @Nullable final Sort sort);
}
