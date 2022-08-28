package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M> implements IUserOwnedRepository<M> {

    @NotNull
    @Override
    public List<M> findAll(@Nullable final String userId) {
        if (userId == null) return Collections.emptyList();
        return models
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public List<M> findAll(@Nullable final String userId, @Nullable final Comparator<M> comparator) {
        @NotNull final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @Nullable
    @Override
    public M add(@Nullable final String userId, @NotNull final M model) {
        if (userId == null) return null;
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public void clear(@Nullable final String userId) {
        @NotNull final List<M> models = findAll(userId);
        removeAll(models);
    }

    @Override
    public boolean existsById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        return findById(userId, id) != null;
    }

    @Nullable
    @Override
    public M findById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        if (userId == null || id == null) return null;
        return models
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    @Override
    public M findByIndex(
            @NotNull final String userId,
            @NotNull final Integer index
    ) {
        return findAll(userId).get(index);
    }

    @Nullable
    @Override
    public M remove(
            @Nullable final String userId,
            @Nullable final M model
    ) {
        if (userId == null || model == null) return null;
        return removeById(userId, model.getId());
    }

    @Nullable
    @Override
    public M removeById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        if (userId == null || id == null) return null;
        @Nullable final M model = findById(userId, id);
        if (model == null) return null;
        return remove(userId, model);
    }

    @Nullable
    @Override
    public M removeByIndex(
            @NotNull final String userId,
            @NotNull final Integer index
    ) {
        @Nullable final M model = findByIndex(userId, index);
        if (model == null) return null;
        return remove(userId, model);
    }

    @Override
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null) throw new UserIdEmptyException();
        removeAll(userId);
    }

}
