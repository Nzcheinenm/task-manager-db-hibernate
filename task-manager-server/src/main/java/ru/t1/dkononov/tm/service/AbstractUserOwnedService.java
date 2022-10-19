package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IUserOwnedService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>> implements IUserOwnedService<M> {

    @NotNull
    protected final R repository;


    public AbstractUserOwnedService(@NotNull final R repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<M> findAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.findAll(userId);
    }

    @Nullable
    @Override
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Comparator<M> comparator
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (comparator == null) return findAll(userId);
        return repository.findAll(userId, comparator);
    }

    @NotNull
    @Override
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Sort sort
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return findAll(userId, sort.getComparator());
    }

    @NotNull
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @NotNull
    @Override
    public M add(@Nullable final String userId, @Nullable final M model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        return repository.add(userId, model);
    }

    @Override
    public @NotNull Collection<M> add(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        return repository.add(models);
    }

    @Override
    public @NotNull Collection<M> set(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        return repository.set(models);
    }

    @Override
    public void clear(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.clear(userId);
    }

    @Override
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(userId, id);
    }

    @Override
    public boolean existsById(@Nullable final String id) throws UserIdEmptyException {
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(id);
    }

    @Nullable
    @Override
    public M add(@Nullable final M model) throws ProjectNotFoundException, UserIdEmptyException {
        if (model == null) throw new ProjectNotFoundException();
        return repository.add(model);
    }

    @Nullable
    @Override
    public M remove(@Nullable final M model) throws UserIdEmptyException {
        return repository.remove(model);
    }

    @Nullable
    @Override
    public M findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findById(userId, id);
    }

    @Nullable
    @Override
    public M findByIndex(@Nullable final String userId, @Nullable final Integer index)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findByIndex(userId, index);
    }

    @Nullable
    @Override
    public M remove(@Nullable final String userId, @Nullable final M model) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.remove(userId, model);
    }

    @NotNull
    @Override
    public M removeById(@Nullable final String userId, @Nullable final String id) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final M model = repository.findById(userId, id);
        remove(userId, model);
        return model;
    }

    @NotNull
    @Override
    public M removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final M model = repository.findByIndex(userId, index);
        repository.remove(userId, model);
        return model;
    }

    @Override
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.removeAll(userId);
    }

}
