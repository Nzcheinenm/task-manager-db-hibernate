package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

public final class SessionRepository extends AbstractUserOwnedRepository<Session> implements ISessionRepository {

    @NotNull final static String table = "tm.tm_session";

    public SessionRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return table;
    }

    @Override
    @Nullable
    public Session add(@Nullable String userId, @NotNull Session model) {
        model.setUserId(userId);
        return add(model);
    }

    @Override
    @NotNull
    @SneakyThrows
    protected Session fetch(@NotNull ResultSet row) {
        @NotNull final Session session = new Session();
        session.setId(row.getString("id"));
        session.setUserId(row.getString("user_id"));
        session.setDate(row.getTimestamp("date"));
        session.setRole(Role.valueOf(row.getString("role")));
        return session;
    }

    @Override
    @NotNull
    @SneakyThrows
    public Session add(@NotNull Session model) {
        @NotNull final String sql = String.format(
                "INSERT INTO %s (id,user_id,date,role)"
                        + "VALUES (?,?,?,?)",
                getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getId());
            statement.setString(2,model.getUserId());
            statement.setTimestamp(3,new Timestamp(model.getDate().getTime()));
            statement.setString(4,model.getRole().toString());
            statement.executeUpdate();
        }
        return model;
    }

}
