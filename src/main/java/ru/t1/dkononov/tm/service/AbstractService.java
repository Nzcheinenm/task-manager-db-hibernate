package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.api.services.IService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends IRepository<M>> implements IService<M> {

    @NotNull
    protected final R repository;

    public AbstractService(@NotNull final R repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Nullable
    @Override
    public List<M> findAll(@Nullable final Comparator<M> comparator) {
        if (comparator == null) return findAll();
        return repository.findAll(comparator);
    }

    @Nullable
    @Override
    public List<M> findAll(@Nullable final Sort sort) {
        if (sort == null) return findAll();
        return findAll(sort.getComparator());
    }

    @Nullable
    @Override
    public M add(@Nullable final M model) throws ProjectNotFoundException {
        if (model == null) throw new ProjectNotFoundException();
        return repository.add(model);
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
    public void clear() {
        repository.clear();
    }

    @Override
    public boolean existsById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(id);
    }

    @Nullable
    @Override
    public M findById(@Nullable final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findById(id);
    }

    @Nullable
    @Override
    public M findByIndex(@Nullable final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findByIndex(index);
    }

    @Nullable
    @Override
    public M remove(@Nullable final M model) {
        return repository.remove(model);
    }

    @Nullable
    @Override
    public M removeById(@Nullable final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @Nullable final M model = repository.findById(id);
        remove(model);
        return model;
    }

    @Nullable
    @Override
    public M removeByIndex(@Nullable final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        @Nullable final M model = repository.findByIndex(index);
        repository.remove(model);
        return model;
    }

}
