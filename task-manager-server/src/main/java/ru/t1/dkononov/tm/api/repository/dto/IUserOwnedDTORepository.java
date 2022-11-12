package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.AbstractUserOwnedModelDTO;

import java.util.List;

public interface IUserOwnedDTORepository<E extends AbstractUserOwnedModelDTO> extends @NotNull IDTORepository<E> {
    void add(@NotNull String userId, E entity);

    void remove(@NotNull String userId, E entity);

    void update(@NotNull String userId, E entity);

    E findByIndex(@Nullable String userId, @Nullable Integer index);

    E findById(@Nullable String userId, @Nullable String id);

    void clear(@NotNull String userId);

    List<E> findAll(@NotNull String userId);

}
