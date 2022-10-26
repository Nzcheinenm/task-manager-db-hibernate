package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.repository.ProjectRepository;

import java.sql.Connection;
import java.sql.SQLException;



public final class ProjectService extends AbstractUserOwnedService<Project, IProjectRepository> implements IProjectService {


    public ProjectService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    public IProjectRepository getRepository(@NotNull Connection connection) {
        return new ProjectRepository(connection);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Project create(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Project result;
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            result = repository.create(userId, name, description);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @NotNull
    @Override
    public Project create(@Nullable final String userId, @Nullable final String name)
            throws AbstractFieldException, SQLException, ProjectNotFoundException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        @NotNull final Connection connection = getConnection();
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            project = repository.add(userId,project);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Project updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Project project;
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            project = repository.findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            project.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Project updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Project project;
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Project changeProjectStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Project project;
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            project = findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Project changeProjectStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Project project;
        try {
            @NotNull final IProjectRepository repository = getRepository(connection);
            project = findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return project;
    }

}
