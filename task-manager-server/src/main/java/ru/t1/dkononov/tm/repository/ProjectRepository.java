package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {

    @NotNull final static String table = "tm.tm_project";

    public ProjectRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return table;
    }

    @NotNull
    @Override
    @SneakyThrows
    protected Project fetch(@NotNull ResultSet row) {
        @NotNull final Project project = new Project();
        project.setId(row.getString("id"));
        project.setName(row.getString("name"));
        project.setDescription(row.getString("description"));
        project.setUserId(row.getString("user_id"));
        project.setStatus(Objects.requireNonNull(Status.toStatus(row.getString("status"))));
        project.setCreated(row.getTimestamp("created"));
        return project;
    }

    @NotNull
    @Override
    public Project create(@NotNull final String userId, @NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(project);
    }

    @NotNull
    @Override
    public Project create(
            @NotNull final String userId,
            @NotNull final String name,
            @NotNull final String description
    ) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        return add(project);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Project add(@NotNull final Project project) {
        @NotNull final String sql = String.format(
                "INSERT INTO %s (id,created,name,description,status,user_id)"
                + "VALUES (?,?,?,?,?,?)",
                getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getId());
            statement.setTimestamp(2,new Timestamp(project.getCreated().getTime()));
            statement.setString(3,project.getName());
            statement.setString(4,project.getDescription());
            statement.setString(5, Status.NOT_STARTED.name());
            statement.setString(6, project.getUserId());
            statement.executeUpdate();
        }
        return project;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Project add(@Nullable String userId, @NotNull Project model) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        model.setUserId(userId);
        return model;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Project update(@NotNull final Project project) {
        @NotNull final String sql = String.format(
                "UPDATE %s SET name = ?, description = ?, status = ? WHERE id = ?", getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,project.getName());
            statement.setString(2,project.getDescription());
            statement.setString(3,project.getStatus().toString());
            statement.executeUpdate();
        }
        return project;
    }

}
