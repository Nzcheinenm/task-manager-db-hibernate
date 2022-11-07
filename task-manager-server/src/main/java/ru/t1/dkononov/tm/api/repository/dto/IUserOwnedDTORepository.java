package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.model.AbstractUserOwnedModelDTO;
import ru.t1.dkononov.tm.repository.model.AbstractUserOwnedRepository;

public interface IUserOwnedDTORepository<E extends AbstractUserOwnedModelDTO> {
    void add(@NotNull String userId, E entity);

    void remove(@NotNull String userId, E entity);

    void update(@NotNull String userId, E entity);
}
