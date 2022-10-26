package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    @NotNull final static String table = "tm_task";

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
        return task;
    }

    @NotNull
    @Override
    public Task add(@NotNull Task model) {
        return null;
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
    public List<Task> findAllByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId
    ) {
        return models
                .stream()
                .filter(m -> m.getProjectId() != null)
                .filter(m -> m.getProjectId().equals(projectId))
                .filter(m -> m.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}
