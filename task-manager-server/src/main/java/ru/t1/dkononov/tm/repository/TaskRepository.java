package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    @NotNull final static String table = "tm.tm_task";

    public TaskRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    public @Nullable Task add(@Nullable String userId, @NotNull Task model) {
        return null;
    }

    @Override
    @NotNull
    protected String getTableName() {
        return table;
    }

    @NotNull
    @Override
    @SneakyThrows
    protected Task fetch(@NotNull ResultSet row) {
        @NotNull final Task task = new Task();
        task.setId(row.getString("id"));
        task.setName(row.getString("name"));
        task.setDescription(row.getString("description"));
        task.setUserId(row.getString("user_id"));
        task.setStatus(Status.toStatus(row.getString("status")));
        task.setCreated(row.getTimestamp("created"));
        task.setProjectId(row.getString("project_id"));
        return task;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task add(@NotNull Task model) {
        @NotNull final String sql = String.format(
                "INSERT INTO %s (id,created,name,description,status,user_id,project_id)"
                        + "VALUES (?,?,?,?,?,?,?)",
                getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getId());
            statement.setTimestamp(2,new Timestamp(model.getCreated().getTime()));
            statement.setString(3,model.getName());
            statement.setString(4,model.getDescription());
            statement.setString(5, Status.NOT_STARTED.name());
            statement.setString(6, model.getUserId());
            statement.setString(7, model.getProjectId());
            statement.executeUpdate();
        }
        return model;
    }

    @NotNull
    @Override
    public Task create(
            @NotNull final String userId,
            @NotNull final String name
    ) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setUserId(userId);
        return add(task);
    }

    @NotNull
    @Override
    public Task create(
            @NotNull final String userId,
            @NotNull final String name,
            @NotNull final String description
    ) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setUserId(userId);
        return add(task);
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Task> findAllByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId
    ) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE project_id = ?", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,projectId);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            @NotNull final List<Task> result = new ArrayList<>();
            while (rowSet.next()) {
                result.add(fetch(rowSet));
            }
            return result;
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task findTaskIdByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId,
            @Nullable final String taskId
    ) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE project_id = ? AND id = ? LIMIT = 1", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,projectId);
            statement.setString(2,taskId);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            return fetch(rowSet);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task update(@NotNull final Task task) {
        @NotNull final String sql = String.format(
                "UPDATE %s SET name = ?, description = ?, status = ?, project_id = ? WHERE id = ?", getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,task.getName());
            statement.setString(2,task.getDescription());
            statement.setString(3,task.getStatus().toString());
            statement.setString(4,task.getProjectId());
            statement.executeUpdate();
        }
        return task;
    }

}
