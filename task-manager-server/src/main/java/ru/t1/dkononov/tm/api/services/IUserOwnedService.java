package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.util.List;

public interface IUserOwnedService<M extends AbstractUserOwnedModel> {

    @NotNull
    List<M> findAll(@Nullable String userId, @Nullable Sort sort) throws UserIdEmptyException;

    @NotNull
    List<M> findAll();

}
