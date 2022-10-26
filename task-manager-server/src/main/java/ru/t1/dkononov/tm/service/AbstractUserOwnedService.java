package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IUserOwnedService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.IndexIncorrectException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>> implements IUserOwnedService<M> {

    @NotNull
    protected IConnectionService connectionService;

    public AbstractUserOwnedService(@NotNull final IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    protected abstract IUserOwnedRepository<M> getRepository(@NotNull final Connection connection);

    @NotNull
    protected Connection getConnection() {
        return connectionService.getConnection();
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.findAll(userId);
        }

    }

    @Nullable
    @Override
    @SneakyThrows
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Comparator<M> comparator
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            if (comparator == null) return findAll(userId);
            return repository.findAll(userId, comparator);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(
            @Nullable final String userId,
            @Nullable final Sort sort
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            if (sort == null) return findAll(userId);
            return findAll(userId, sort.getComparator());
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.findAll();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public M add(@Nullable final String userId, @Nullable final M model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            result = repository.add(userId,model);
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
    @SneakyThrows
    public Collection<M> add(@NotNull Collection<M> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final Connection connection = getConnection();
        @Nullable final Collection<M> result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
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
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
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
    public void clear(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            repository.clear(userId);
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
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.existsById(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String id) throws UserIdEmptyException {
        if (id == null || id.isEmpty()) return false;
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.existsById(id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M add(@Nullable final M model) throws ProjectNotFoundException, UserIdEmptyException {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
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

    @Nullable
    @Override
    @SneakyThrows
    public M remove(@NotNull final String userId, @Nullable final M model) throws UserIdEmptyException {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            result = repository.remove(userId,model);
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
    public M findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.findById(userId, id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findByIndex(@Nullable final String userId, @Nullable final Integer index)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            return repository.findByIndex(userId, index);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public M removeById(@Nullable final String userId, @Nullable final String id) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            result = repository.findById(userId, id);
            remove(userId, result);
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
    @SneakyThrows
    public M removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Connection connection = getConnection();
        @Nullable final M result;
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            result = repository.findByIndex(userId, index);
            repository.remove(userId, result);
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
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final Connection connection = getConnection();
        try {
            @NotNull final IUserOwnedRepository<M> repository = getRepository(connection);
            repository.removeAll(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
