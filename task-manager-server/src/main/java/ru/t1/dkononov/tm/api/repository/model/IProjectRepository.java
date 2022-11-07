package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<Project> findAll();

    @NotNull List<Project> findAll(@NotNull String userId);

    @NotNull List<Project> findAll(@NotNull String userId, @NotNull Sort sort);

    @NotNull List<Project> findAll(@NotNull String userId, @NotNull Comparator<ProjectDTO> comparator);

    @NotNull Project findById(@NotNull String id);

    @Nullable Project findById(@NotNull String userId, @NotNull String id);

    @NotNull Project findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);
}
