package ru.t1.dkononov.tm.api.services.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

import java.sql.SQLException;

public interface IProjectDTOService extends IUserOwnedDTOService<ProjectDTO> {
    @NotNull
    @SneakyThrows
    ProjectDTO create(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    )
            throws AbstractFieldException;

    @NotNull ProjectDTO create(@Nullable String userId, @Nullable String name)
            throws AbstractFieldException, SQLException, ProjectNotFoundException;

    @Nullable
    @SneakyThrows
    ProjectDTO updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    )
            throws AbstractException;

    @Nullable
    @SneakyThrows
    ProjectDTO updateByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @Nullable String name,
            @Nullable String description
    )
            throws AbstractException;

    @Nullable
    @SneakyThrows
    ProjectDTO changeProjectStatusById(
            @Nullable String userId,
            @Nullable String id,
            @NotNull Status status
    )
            throws AbstractException;

    @Nullable
    @SneakyThrows
    ProjectDTO changeProjectStatusByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @NotNull Status status
    ) throws AbstractException;

    @NotNull
    ProjectDTO findById(@NotNull String userId, @Nullable String projectId);

    @NotNull
    ProjectDTO removeById(@NotNull String userId, @Nullable String projectId);

    boolean existsById(@NotNull String userId, @NotNull String projectId);


}
