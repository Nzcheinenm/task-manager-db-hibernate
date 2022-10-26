package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends IRepository<M>> implements IService<M> {

    @NotNull
    protected final IConnectionService connectionService;

    public AbstractService(@NotNull final IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    protected Connection getConnection() {
        return connectionService.getConnection();
    }

    @NotNull
    protected abstract IRepository<M> getRepository(@NotNull final Connection connection);

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            return repository.findAll();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final Comparator<M> comparator) {
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            if (comparator == null) return findAll();
            return repository.findAll(comparator);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final Sort sort) {
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            if (sort == null) return findAll();
            return findAll(sort.getComparator());
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M add(@Nullable final M model) throws ProjectNotFoundException {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.add(model);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<M> add(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final Connection connection = getConnection();
        @Nullable final Collection<M> result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.add(models);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<M> set(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final Connection connection = getConnection();
        @Nullable final Collection<M> result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.set(models);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final Connection connection = getConnection();
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            repository.clear();
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            return repository.existsById(id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findById(@Nullable final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            return repository.findById(id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findByIndex(@Nullable final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IRepository<M> repository = getRepository(connection);
            return repository.findByIndex(index);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M remove(@Nullable final M model) {
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.remove(model);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M removeById(@Nullable final String id) throws IdEmptyException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.findById(id);
            remove(result);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M removeByIndex(@Nullable final Integer index) throws IndexIncorrectException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IRepository<M> repository = getRepository(connection);
            result = repository.findByIndex(index);
            repository.remove(result);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return result;
    }

}
