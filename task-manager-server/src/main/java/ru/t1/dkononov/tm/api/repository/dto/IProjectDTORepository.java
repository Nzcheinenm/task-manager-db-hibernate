package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import java.util.Comparator;
import java.util.List;

public interface IProjectDTORepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<ProjectDTO> findAll();

    @NotNull List<ProjectDTO> findAll(@NotNull String userId);

    @NotNull List<ProjectDTO> findAll(@NotNull String userId, @NotNull Sort sort);

    @NotNull List<ProjectDTO> findAll(@NotNull String userId, @NotNull Comparator<ProjectDTO> comparator);

    @NotNull ProjectDTO findById(@NotNull String id);

    @Nullable ProjectDTO findById(@NotNull String userId, @NotNull String id);

    @NotNull ProjectDTO findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);
}
