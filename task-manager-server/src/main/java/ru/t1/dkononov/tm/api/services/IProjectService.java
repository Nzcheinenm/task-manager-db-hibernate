package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface IProjectService {

    @NotNull
    @SneakyThrows
    List<ProjectDTO> findAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<ProjectDTO> findAll();

    @Nullable
    @SneakyThrows
    List<ProjectDTO> findAll(
            @Nullable String userId,
            @Nullable Comparator comparator
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<ProjectDTO> findAll(
            @Nullable String userId,
            @Nullable Sort sort
    ) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    ProjectDTO add(@Nullable String userId, @Nullable ProjectDTO model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<ProjectDTO> add(@NotNull Collection<ProjectDTO> models);

    @NotNull
    @SneakyThrows
    Collection<ProjectDTO> set(@NotNull Collection<ProjectDTO> models);

    @SneakyThrows
    void clear(@Nullable String userId) throws UserIdEmptyException;

    @SneakyThrows
    boolean existsById(@Nullable String userId, @Nullable String id) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    ProjectDTO remove(@NotNull String userId, @Nullable ProjectDTO model) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    ProjectDTO findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @Nullable
    @SneakyThrows
    ProjectDTO findByIndex(@Nullable String userId, @Nullable Integer index)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectDTO removeById(@Nullable String userId, @Nullable String id) throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectDTO removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull
    ProjectDTO create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException;

    @NotNull
    ProjectDTO create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, SQLException, ProjectNotFoundException;

    @Nullable ProjectDTO updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable ProjectDTO updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable ProjectDTO changeProjectStatusById(@Nullable String userId, @Nullable String id, @NotNull Status status) throws AbstractException;

    @Nullable ProjectDTO changeProjectStatusByIndex(@Nullable String userId, @Nullable Integer index, @NotNull Status status) throws AbstractException;

}
