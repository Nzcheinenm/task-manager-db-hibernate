package ru.t1.dkononov.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.model.ProjectRepository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;


public final class ProjectService extends AbstractUserOwnedService<Project, ProjectRepository> implements IProjectService {

    public ProjectService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            result = new Project();
            result.setUser(entityManager.find(User.class, userId));
            result.setName(name);
            result.setDescription(description);
            repository.add(userId, result);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            project.setUser(entityManager.find(User.class, userId));
            project.setName(name);
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            repository.add(userId, project);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            project = repository.findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            project.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            project = findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
    ) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return project;
    }

    @Override
    @NotNull
    protected IUserOwnedRepository<Project> getRepository(@NotNull final EntityManager entityManager) {
        return new ProjectRepository(entityManager);
    }

    @Override
    public @Nullable List<Project> findAll(@Nullable Sort sort) {
        return null;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project findById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            return repository.findById(userId, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public Project removeById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Project result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Project> repository = getRepository(entityManager);
            result = repository.findById(userId, id);
            remove(userId, result);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public boolean existsById(String userId, String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new IdEmptyException();
        return findById(userId, projectId) != null;
    }

}
