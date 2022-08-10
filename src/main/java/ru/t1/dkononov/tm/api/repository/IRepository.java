package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.Comparator;
import java.util.List;

public interface IRepository<M extends AbstractModel> {

    List<M> findAll();

    List<M> findAll(final Comparator<M> comparator);

    M add(final M m) throws ProjectNotFoundException;

    void clear();

    boolean existsById(final String id);

    M findById(final String id) throws IdEmptyException;

    M findByIndex(final Integer index) throws IndexIncorrectException;

    M remove(final M m);

    M removeById(final String id) throws IdEmptyException;

    M removeByIndex(final Integer index) throws IndexIncorrectException;

    void removeAll(List<M> modelsRemove);
}
