package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    protected final List<M> models = new ArrayList<>();

    @Override
    public List<M> findAll() {
        return models;
    }

    @Override
    public List<M> findAll(final Comparator<M> comparator) {
        final List<M> result = new ArrayList<>(models);
        result.sort(comparator);
        return result;
    }

    @Override
    public M add(final M model) {
        models.add(model);
        return model;
    }

    @Override
    public void clear() {
        models.clear();
    }

    @Override
    public boolean existsById(final String id) {
        return findById(id) != null;
    }

    @Override
    public M findById(final String id) {
        return models
                .stream()
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public M findByIndex(final Integer index) {
        return models.get(index);
    }

    @Override
    public M remove(final M model) {
        if (model == null) return null;
        models.remove(model);
        return model;
    }

    @Override
    public M removeById(final String id) {
        final M model = findById(id);
        if (model == null) return null;
        remove(model);
        return model;
    }

    @Override
    public M removeByIndex(final Integer index) {
        final M model = findByIndex(index);
        if (model == null) return null;
        remove(model);
        return model;
    }

    @Override
    public void removeAll(final List<M> modelsRemove) {
        if (modelsRemove == null) return;
        modelsRemove.forEach(models::remove);
    }

}
