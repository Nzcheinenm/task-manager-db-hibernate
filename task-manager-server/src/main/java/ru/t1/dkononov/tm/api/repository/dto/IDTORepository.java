package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.AbstractModelDTO;

import java.util.List;

public interface IDTORepository<E extends AbstractModelDTO> {
    void add(@NotNull E entity);

    void clear();

    @NotNull
    List<E> findAll();

    @NotNull
    E findById(@NotNull String id);

    void remove(@NotNull E entity);

    void update(@NotNull E entity);

    E findByIndex(@Nullable Integer index);
}
