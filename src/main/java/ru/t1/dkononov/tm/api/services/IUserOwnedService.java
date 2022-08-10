package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.List;

public interface IUserOwnedService<M extends AbstractUserOwnedModel> extends IUserOwnedRepository<M> {

    List<M> findAll(String userId, Sort sort) throws UserIdEmptyException;


}
