package ru.t1.dkononov.tm.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IUserOwnedDTORepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.dto.IProjectDTOService;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.repository.dto.ProjectDTORepository;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public final class ProjectDTOService extends AbstractUserOwnedDTOService<ProjectDTO, ProjectDTORepository>
        implements IProjectDTOService {

    public ProjectDTOService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    @Override
    @SneakyThrows
    public ProjectDTO create(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final ProjectDTO result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            result = new ProjectDTO();
            result.setUserId(userId);
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
    public ProjectDTO create(@Nullable final String userId, @Nullable final String name)
            throws AbstractFieldException, SQLException, ProjectNotFoundException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull ProjectDTO project = new ProjectDTO();
        project.setUserId(userId);
        project.setName(name);
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            project.setUserId(userId);
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
    public ProjectDTO updateById(
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
        @Nullable final ProjectDTO project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            project = repository.findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            project.setUserId(userId);
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
    public ProjectDTO updateByIndex(
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
        @Nullable final ProjectDTO project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
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
    public ProjectDTO changeProjectStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final ProjectDTO project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            project = findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
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
    public ProjectDTO changeProjectStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    ) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final ProjectDTO project;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
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
    protected IUserOwnedDTORepository<ProjectDTO> getRepository(@NotNull final EntityManager entityManager) {
        return new ProjectDTORepository(entityManager);
    }

    @NotNull
    @SneakyThrows
    public ProjectDTO findById(@NotNull String userId, @Nullable String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            return repository.findById(userId, projectId);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @SneakyThrows
    public ProjectDTO removeById(@NotNull String userId, @Nullable String projectId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final ProjectDTO result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<ProjectDTO> repository = getRepository(entityManager);
            result = repository.findById(userId, projectId);
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

    public boolean existsById(@NotNull String userId, @NotNull String projectId) {
        return findById(userId, projectId) != null;
    }

}
