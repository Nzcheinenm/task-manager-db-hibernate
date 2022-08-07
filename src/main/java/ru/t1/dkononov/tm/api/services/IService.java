package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.List;

public interface IService<M extends AbstractModel> extends IRepository<M> {

    List<M> findAll(Sort sort);


}
