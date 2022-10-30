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
import ru.t1.dkononov.tm.model.Project;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface IProjectService {

    @NotNull
    @SneakyThrows
    List<Project> findAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<Project> findAll();

    @Nullable
    @SneakyThrows
    List<Project> findAll(
            @Nullable String userId,
            @Nullable Comparator comparator
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<Project> findAll(
            @Nullable String userId,
            @Nullable Sort sort
    ) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Project add(@Nullable String userId, @Nullable Project model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<Project> add(@NotNull Collection<Project> models);

    @NotNull
    @SneakyThrows
    Collection<Project> set(@NotNull Collection<Project> models);

    @SneakyThrows
    void clear(@Nullable String userId) throws UserIdEmptyException;

    @SneakyThrows
    boolean existsById(@Nullable String userId, @Nullable String id) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Project remove(@NotNull String userId, @Nullable Project model) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Project findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @Nullable
    @SneakyThrows
    Project findByIndex(@Nullable String userId, @Nullable Integer index)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    Project removeById(@Nullable String userId, @Nullable String id) throws AbstractException;

    @NotNull
    @SneakyThrows
    Project removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull
    Project create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException;

    @NotNull
    Project create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, SQLException, ProjectNotFoundException;

    @Nullable Project updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable Project updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable Project changeProjectStatusById(@Nullable String userId, @Nullable String id, @NotNull Status status) throws AbstractException;

    @Nullable Project changeProjectStatusByIndex(@Nullable String userId, @Nullable Integer index, @NotNull Status status) throws AbstractException;

}
