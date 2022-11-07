package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.UserDTO;

import java.util.List;

public interface IUserDTORepository {
    void clear();

    @NotNull List<UserDTO> findAll();

    @NotNull UserDTO findById(@NotNull String id);

    @NotNull UserDTO findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    @Nullable UserDTO findByLogin(@NotNull String login);

    @Nullable UserDTO findByEmail(@NotNull String email);
}
