package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IRepository;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;
import ru.t1.dkononov.tm.model.AbstractModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    @NotNull
    protected final Connection connection;

    public AbstractRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    protected abstract String getTableName();

    @NotNull
    protected String getSortType(@NotNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return "created";
        else if (comparator == StatusComparator.INSTANCE) return "status";
        else  return "name";
    }

    @NotNull
    protected abstract  M fetch(@NotNull final ResultSet row);

    @NotNull
    @Override
    public abstract M add(@NotNull final M model);

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        @NotNull final List<M> result = new ArrayList<>();
        @NotNull final String sql = String.format("SELECT * FROM %s", getTableName());
        try (@NotNull final Statement statement = connection.createStatement();
            @NotNull final ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) result.add(fetch(resultSet));
        }
        return result;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<M> findAll(@Nullable final Comparator<M> comparator) {
        @NotNull final List<M> result = new ArrayList<>();
        @NotNull final String sql = String.format("SELECT * FROM %s ORDER BY %s", getTableName(),getSortType(comparator));
        try (@NotNull final Statement statement = connection.createStatement();
             @NotNull final ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) result.add(fetch(resultSet));
        }
        return result;
    }

    @Override
    @NotNull
    public Collection<M> add(@NotNull final Collection<M> models) {
       @NotNull final List<M> result = new ArrayList<>();
       for (M model : models) {
           result.add(add(model));
       }
       return result;
    }

    @Override
    public @NotNull Collection<M> set(@NotNull Collection<M> models) {
        clear();
        return add(models);
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final String sql = String.format("DELETE FROM %s", getTableName());
        try (@NotNull final Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public boolean existsById(final String id) {
        return findById(id) != null;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findById(@NotNull final String id) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE id = ? LIMIT 1", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,id);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            return fetch(rowSet);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public M findByIndex(@NotNull final Integer index) {
        return findAll().get(index);
    }

    @Nullable
    @Override
    @SneakyThrows
    public M remove(@Nullable final M model) {
        @NotNull final String sql = String.format("DELETE FROM %s WHERE id = ?",getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,model.getId());
            statement.executeUpdate();
        }
        return model;
    }

    @Nullable
    @Override
    @SneakyThrows
    public M removeById(@NotNull final String id) {
        @NotNull final M model = findById(id);
        @NotNull final String sql = String.format("DELETE FROM %s WHERE id = ?",getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,id);
            statement.executeUpdate();
        }
        return model;
    }

    @Nullable
    @Override
    public M removeByIndex(@NotNull final Integer index) {
        @Nullable final M model = findByIndex(index);
        if (model == null) return null;
        remove(model);
        return model;
    }

    @Override
    public void removeAll(@Nullable final List<M> modelsRemove) {
        if (modelsRemove == null) return;
        modelsRemove.forEach(modelsRemove::remove);
    }

    @SneakyThrows
    public long getCount() {
        @NotNull final String query = String.format("SELECT COUNT(*) FROM %s",getTableName());
        try (@NotNull final Statement statement = connection.createStatement();
            @NotNull final ResultSet resultSet = statement.executeQuery(query)) {
            resultSet.next();
            return  resultSet.getLong("count");
        }
    }

}
