package ru.t1.dkononov.tm.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IUserOwnedDTORepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.dto.ISessionDTOService;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.repository.dto.SessionDTORepository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class SessionDTOService extends AbstractUserOwnedDTOService<SessionDTO, SessionDTORepository> implements ISessionDTOService {

    public SessionDTOService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    protected @NotNull IUserOwnedDTORepository getRepository(@NotNull EntityManager entityManager) {
        return new SessionDTORepository(entityManager);
    }

    @Nullable
    @Override
    @SneakyThrows
    public SessionDTO findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            return repository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<SessionDTO> findAll() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public SessionDTO add(@Nullable final SessionDTO model) {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final SessionDTO result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            repository.add(model);
            result = repository.findById(model.getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Collection<SessionDTO> add(@NotNull Collection<SessionDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            models.forEach(repository::add);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return models;
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            repository.clear(userId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public SessionDTO remove(@NotNull final String userId, @Nullable final SessionDTO model) {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            model.setUserId(userId);
            repository.remove(model);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return model;
    }

    @Override
    @Nullable
    public List<SessionDTO> findAll(@Nullable Sort sort) {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            if (sort == null) return findAll();
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            return repository.findById(id) != null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @NotNull
    public List<SessionDTO> findAll(@Nullable String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedDTORepository<SessionDTO> repository = getRepository(entityManager);
            return repository.findAll(userId);
        } finally {
            entityManager.close();
        }
    }
}
