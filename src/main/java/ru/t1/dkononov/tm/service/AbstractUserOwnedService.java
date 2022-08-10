package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IUserOwnedService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>> implements IUserOwnedService<M> {

    protected final R repository;

    public AbstractUserOwnedService(final R repository) {
        this.repository = repository;
    }

    @Override
    public List<M> findAll(final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.findAll(userId);
    }

    @Override
    public List<M> findAll(final String userId, final Comparator<M> comparator) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (comparator == null) return findAll(userId);
        return repository.findAll(userId, comparator);
    }

    @Override
    public List<M> findAll(final String userId, final Sort sort) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return findAll(userId, sort.getComparator());
    }

    @Override
    public M add(final String userId, final M model) throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        return repository.add(userId, model);
    }

    @Override
    public void clear(final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.clear(userId);
    }

    @Override
    public boolean existsById(final String userId, final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(userId, id);
    }

    @Override
    public M findById(final String userId, final String id) throws IdEmptyException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findById(userId, id);
    }

    @Override
    public M findByIndex(final String userId, final Integer index) throws IndexIncorrectException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findByIndex(userId, index);
    }

    @Override
    public M remove(final String userId, final M model) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.remove(userId, model);
    }

    @Override
    public M removeById(final String userId, final String id) throws IdEmptyException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final M model = repository.findById(userId, id);
        remove(userId, model);
        return model;
    }

    @Override
    public M removeByIndex(final String userId, final Integer index) throws IndexIncorrectException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final M model = repository.findByIndex(userId, index);
        repository.remove(userId, model);
        return model;
    }

}
