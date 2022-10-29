package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M> implements IUserOwnedRepository<M> {

    public AbstractUserOwnedRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @NotNull
    @Override
    public abstract M add(@NotNull final String userId, @NotNull final M model);

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final String userId) {
        @NotNull final List<M> result = new ArrayList<>();
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE user_id = ?",getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,userId);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            while (rowSet.next())
                result.add(fetch(rowSet));
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final String userId, @Nullable final Comparator<M> comparator) {
        @NotNull final List<M> result = new ArrayList<>();
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE user_id = ? ORDER BY %s",getTableName(),getSortType(comparator));
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,userId);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            while (rowSet.next()) result.add(fetch(rowSet));
        }
        return result;
    }

    @Override
    @NotNull
    public Collection<M> add(@NotNull Collection<M> models) {
        return add(models);
    }

    @Override
    public @NotNull Collection<M> set(@NotNull Collection<M> models) {
        clear();
        return add(models);
    }

    @Override
    public void clear(@Nullable final String userId) {
        @NotNull final List<M> models = findAll(userId);
        removeAll(models);
    }

    @Override
    public boolean existsById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        return findById(userId, id) != null;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE id = ? AND user_id LIMIT 1", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,id);
            statement.setString(2,userId);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            return fetch(rowSet);
        }
    }

    @Nullable
    @Override
    public M findByIndex(
            @NotNull final String userId,
            @NotNull final Integer index
    ) {
        return findAll(userId).get(index);
    }

    @Nullable
    @Override
    public M remove(
            @Nullable final String userId,
            @Nullable final M model
    ) {
        if (userId == null || model == null) return null;
        model.setUserId(userId);
        return remove(model);
    }

    @Nullable
    @Override
    public M removeById(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        if (userId == null || id == null) return null;
        @Nullable final M model = findById(userId, id);
        if (model == null) return null;
        return remove(userId, model);
    }

    @Nullable
    @Override
    public M removeByIndex(
            @NotNull final String userId,
            @NotNull final Integer index
    ) {
        @Nullable final M model = findByIndex(userId, index);
        if (model == null) return null;
        return remove(userId, model);
    }

    @Override
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null) throw new UserIdEmptyException();
        clear(userId);
    }

}
