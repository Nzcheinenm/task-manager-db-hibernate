package ru.t1.dkononov.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ISessionService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.model.SessionRepository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class SessionService extends AbstractUserOwnedService<Session, SessionRepository> implements ISessionService {

    public SessionService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    protected @NotNull IUserOwnedRepository getRepository(@NotNull EntityManager entityManager) {
        return new SessionRepository(entityManager);
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
            return repository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session add(@Nullable final Session model) {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Session result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
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
    public Collection<Session> add(@NotNull Collection<Session> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
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
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
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
    public Session remove(@NotNull final String userId, @Nullable final Session model) {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
            model.setUser(entityManager.find(User.class, userId));
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
    public List<Session> findAll(@Nullable Sort sort) {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
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
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
            return repository.findById(id) != null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @NotNull
    public List<Session> findAll(@Nullable String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Session> repository = getRepository(entityManager);
            return repository.findAll(userId);
        } finally {
            entityManager.close();
        }
    }

}
