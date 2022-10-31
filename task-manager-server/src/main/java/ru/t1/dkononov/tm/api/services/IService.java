package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.dto.model.AbstractModelDTO;

import java.util.List;

public interface IService<M extends AbstractModelDTO> {

    @Nullable
    List<M> findAll(@Nullable Sort sort);


}
