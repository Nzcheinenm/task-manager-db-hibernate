package ru.t1.dkononov.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.IRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractModel;
import ru.t1.dkononov.tm.repository.model.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends AbstractRepository<M>> implements IService<M> {

    @NotNull
    protected final IConnectionService connectionService;

    protected AbstractService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    protected EntityManager getEntityManager() {
        return connectionService.getEntityManager();
    }

    @NotNull
    protected abstract IRepository<M> getRepository(@NotNull final EntityManager entityManager);

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            entityManager.getTransaction().begin();
            repository.clear();
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
    public boolean existsById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            return repository.findById(id) != null;
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
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M add(@Nullable final M model) {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            repository.add(model);
            entityManager.getTransaction().commit();
            result = findById(model.getId());
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
            @NotNull final IRepository<M> repository = getRepository(entityManager);
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
            @NotNull final IRepository<M> repository = getRepository(entityManager);
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
    public M remove(@Nullable final M model) throws UserIdEmptyException {
        @NotNull EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<M> repository = getRepository(entityManager);
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

    @Nullable
    @Override
    @SneakyThrows
    public M findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            return repository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findByIndex(@Nullable final Integer index)
            throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            return repository.findByIndex(index);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public M removeById(@Nullable final String id) throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            result = repository.findById(id);
            remove(result);
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
    public M removeByIndex(@Nullable final Integer index) throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final M result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            result = repository.findByIndex(index);
            repository.remove(result);
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
    public void removeAll() throws UserIdEmptyException {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<M> repository = getRepository(entityManager);
            repository.clear();
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
