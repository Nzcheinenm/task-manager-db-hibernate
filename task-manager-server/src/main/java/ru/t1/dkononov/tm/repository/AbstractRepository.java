package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    @NotNull
    protected final List<M> models = new ArrayList<>();

    @NotNull
    @Override
    public List<M> findAll() {
        return models;
    }

    @NotNull
    @Override
    public List<M> findAll(@Nullable final Comparator<M> comparator) {
        @NotNull final List<M> result = new ArrayList<>(models);
        result.sort(comparator);
        return result;
    }

    @NotNull
    @Override
    public M add(@NotNull final M model) {
        models.add(model);
        return model;
    }

    @Override
    public @NotNull Collection<M> add(@NotNull Collection<M> models) {
        this.models.addAll(models);
        return models;
    }

    @Override
    public @NotNull Collection<M> set(@NotNull Collection<M> models) {
        clear();
        return add(models);
    }

    @Override
    public void clear() {
        models.clear();
    }

    @Override
    public boolean existsById(final String id) {
        return findById(id) != null;
    }

    @Nullable
    @Override
    public M findById(@NotNull final String id) {
        return models
                .stream()
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    @Override
    public M findByIndex(@NotNull final Integer index) {
        return models.get(index);
    }

    @Nullable
    @Override
    public M remove(@Nullable final M model) {
        if (model == null) return null;
        models.remove(model);
        return model;
    }

    @Nullable
    @Override
    public M removeById(@NotNull final String id) {
        @Nullable final M model = findById(id);
        if (model == null) return null;
        remove(model);
        return model;
    }

    @Nullable
    @Override
    public M removeByIndex(@NotNull final Integer index) {
        @Nullable final M model = findByIndex(index);
        if (model == null) return null;
        remove(model);
        return model;
    }

    @Override
    public void removeAll(@Nullable final List<M> modelsRemove) {
        if (modelsRemove == null) return;
        modelsRemove.forEach(models::remove);
    }

}
