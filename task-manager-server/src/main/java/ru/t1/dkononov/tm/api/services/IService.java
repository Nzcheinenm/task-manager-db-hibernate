package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.util.List;

public interface IService<M extends AbstractModel> {

    @Nullable
    List<M> findAll(@Nullable Sort sort);


}
