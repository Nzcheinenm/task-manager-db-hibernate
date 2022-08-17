package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public interface IUserOwnedRepository<M extends AbstractUserOwnedModel> {

    List<M> findAll(String userId) throws UserIdEmptyException;

    List<M> findAll(String userId, Comparator<M> comparator) throws UserIdEmptyException;

    M add(String userId, M m) throws ProjectNotFoundException, UserIdEmptyException;

    void clear(String userId) throws UserIdEmptyException;

    boolean existsById(String userId, String id) throws UserIdEmptyException;

    M findById(String userId, String id) throws IdEmptyException, UserIdEmptyException;

    M findByIndex(String userId, Integer index) throws IndexIncorrectException, UserIdEmptyException;

    M remove(String userId, M m) throws UserIdEmptyException;

    M removeById(String userId, String id) throws IdEmptyException, UserIdEmptyException;

    M removeByIndex(String userId, Integer index) throws IndexIncorrectException, UserIdEmptyException;

    void removeAll(String userId) throws UserIdEmptyException;
}
