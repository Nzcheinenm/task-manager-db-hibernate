package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.model.Session;

import java.sql.Connection;
import java.sql.ResultSet;

public final class SessionRepository extends AbstractUserOwnedRepository<Session> implements ISessionRepository {

    @NotNull final static String table = "tm_session";

    public SessionRepository(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    public @Nullable Session add(@Nullable String userId, @NotNull Session model) {

    }

    @Override
    protected String getTableName() {
        return table;
    }

    @Override
    protected @NotNull Session fetch(@NotNull ResultSet row) {
        return null;
    }

    @Override
    public @NotNull Session add(@NotNull Session model) {
        return null;
    }

}
