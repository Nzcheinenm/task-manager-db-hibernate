package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.api.services.IService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends IRepository<M>> implements IService<M> {

    protected final R repository;

    public AbstractService(final R repository) {
        this.repository = repository;
    }

    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Override
    public List<M> findAll(final Comparator<M> comparator) {
        if (comparator == null) return findAll();
        return repository.findAll(comparator);
    }

    @Override
    public List<M> findAll(final Sort sort) {
        if (sort == null) return findAll();
        return findAll(sort.getComparator());
    }

    @Override
    public M add(final M model) throws ProjectNotFoundException {
        if (model == null) throw new ProjectNotFoundException();
        return repository.add(model);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public boolean existsById(String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(id);
    }

    @Override
    public M findById(final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findById(id);
    }

    @Override
    public M findByIndex(final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findByIndex(index);
    }

    @Override
    public M remove(final M model) {
        return repository.remove(model);
    }

    @Override
    public M removeById(final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final M model = repository.findById(id);
        remove(model);
        return model;
    }

    @Override
    public M removeByIndex(final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final M model = repository.findByIndex(index);
        repository.remove(model);
        return model;
    }

}
