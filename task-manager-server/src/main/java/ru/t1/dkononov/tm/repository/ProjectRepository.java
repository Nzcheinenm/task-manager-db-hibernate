package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {

    @NotNull final static String table = "tm_project";

    public ProjectRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @NotNull
    @Override
    public Project create(@NotNull final String userId, @NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(project);
    }

    @Override
    protected String getTableName() {
        return table;
    }

    @Override
    protected @NotNull Project fetch(@NotNull ResultSet row) {
        return null;
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
                "INSERT INTO %s (id,created,name,description,status,userId)"
                + "VALUES (?,?,?,?,?,?,?,?)",
                getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UUID.randomUUID().toString());
            statement.setTimestamp(2,new Timestamp(project.getCreated().getTime()));
            statement.setString(3,project.getName());
            statement.setString(4,project.getDescription());
            statement.setString(5, Status.NOT_STARTED.name());
            statement.setString(6, project.getUserId());
            statement.executeUpdate();
        }
        return project;
    }

    @Override
    public @Nullable Project add(@Nullable String userId, @NotNull Project model) {
        return null;
    }
}
