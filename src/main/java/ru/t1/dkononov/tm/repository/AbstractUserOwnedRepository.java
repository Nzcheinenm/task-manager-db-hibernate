package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel> extends AbstractRepository<M> implements IUserOwnedRepository<M> {

    @Override
    public List<M> findAll(String userId) {
        if (userId == null) return Collections.emptyList();
        return models
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<M> findAll(final String userId, final Comparator<M> comparator) {
        final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @Override
    public M add(final String userId, final M model) {
        if (userId == null) return null;
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public void clear(final String userId) {
        final List<M> models = findAll(userId);
        removeAll(models);
    }

    @Override
    public boolean existsById(final String userId, final String id) {
        return findById(userId, id) != null;
    }

    @Override
    public M findById(final String userId, final String id) {
        if (userId == null || id == null) return null;
        return models
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public M findByIndex(final String userId, final Integer index) {
        return findAll(userId).get(index);
    }

    @Override
    public M remove(final String userId, final M model) {
        if (userId == null || model == null) return null;
        return removeById(userId, model.getId());
    }

    @Override
    public M removeById(final String userId, final String id) {
        if (userId == null || id == null) return null;
        final M model = findById(userId, id);
        if (model == null) return null;
        return remove(userId, model);
    }

    @Override
    public M removeByIndex(final String userId, final Integer index) {
        final M model = findByIndex(userId, index);
        if (model == null) return null;
        return remove(userId, model);
    }

}
