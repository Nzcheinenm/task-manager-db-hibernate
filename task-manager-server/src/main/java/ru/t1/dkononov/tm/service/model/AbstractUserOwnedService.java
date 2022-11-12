package ru.t1.dkononov.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IUserDTORepository;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.dto.UserDTORepository;
import ru.t1.dkononov.tm.repository.model.AbstractUserOwnedRepository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends AbstractUserOwnedRepository<M>>
        extends AbstractService<M, R> implements ru.t1.dkononov.tm.api.services.model.IUserOwnedService<M> {

    public AbstractUserOwnedService(@Nullable final IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    protected abstract IUserOwnedRepository getRepository(@NotNull final EntityManager entityManager);

    @NotNull
    protected IUserDTORepository getUserRepository(@NotNull final EntityManager entityManager) {
        return new UserDTORepository(entityManager);
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            entityManager.getTransaction().begin();
            repository.clear(userId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            return repository.findById(userId, id) != null;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            return repository.findAll(userId);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Comparator comparator
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            if (comparator == null) return findAll(userId);
            return repository.findAll(userId);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Sort sort
    ) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return findAll(userId, sort.getComparator());
    }

    @Nullable
    @Override
    @SneakyThrows
    public M add(@Nullable final String userId, @Nullable final M model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            model.setUser(entityManager.find(User.class, userId));
            repository.add(model);
            entityManager.getTransaction().commit();
            result = findById(userId, model.getId());
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
    public Collection<M> add(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
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
    @NotNull
    @SneakyThrows
    public Collection<M> set(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            models.forEach(repository::update);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return models;
    }


    @Nullable
    @Override
    @SneakyThrows
    public M remove(@NotNull final String userId, @Nullable final M model) throws UserIdEmptyException {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            model.setUser(entityManager.find(User.class, userId));
            repository.remove(userId, model);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return model;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            return repository.findById(userId, id);
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findByIndex(@Nullable final String userId, @Nullable final Integer index)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            return repository.findByIndex(userId, index);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public M removeById(@Nullable final String userId, @Nullable final String id) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
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

    @NotNull
    @Override
    @SneakyThrows
    public M removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            result = repository.findByIndex(userId, index);
            result.setUser(entityManager.find(User.class, userId));
            repository.remove(userId, result);
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
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<M> repository = getRepository(entityManager);
            repository.clear(userId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
