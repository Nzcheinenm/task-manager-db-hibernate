package ru.t1.dkononov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @NotNull
    private static final String table = "tm.tm_user";

    public UserRepository(@NotNull Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return table;
    }

    @Override
    @NotNull
    @SneakyThrows
    protected User fetch(@NotNull ResultSet row) {
        @NotNull final User user = new User();
        user.setId(row.getString("id"));
        user.setLogin(row.getString("login"));
        user.setPasswordHash(row.getString("password"));
        user.setFirstName(row.getString("first_name"));
        user.setMiddleName(row.getString("middle_name"));
        user.setLastName(row.getString("last_name"));
        user.setEmail(row.getString("email"));
        user.setRole(Role.valueOf(row.getString("role")));
        user.setLocked(row.getBoolean("locked"));
        return user;
    }

    @Override
    @NotNull
    @SneakyThrows
    public User add(@NotNull User model) {
        @NotNull final String sql = String.format(
                "INSERT INTO %s (id,login,password,first_name,middle_name,last_name,email,role,locked)"
                        + "VALUES (?,?,?,?,?,?,?,?,?)",
                getTableName()
        );
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,model.getId());
            statement.setString(2,model.getLogin());
            statement.setString(3,model.getPasswordHash());
            statement.setString(4,model.getFirstName());
            statement.setString(5,model.getMiddleName());
            statement.setString(6,model.getLastName());
            statement.setString(7,model.getEmail());
            statement.setString(8,model.getRole().toString());
            statement.setBoolean(9, false);
            statement.executeUpdate();
        }
        return model;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByLogin(@NotNull final String login) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE login = ? LIMIT 1", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,login);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            return fetch(rowSet);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByEmail(@NotNull final String email) {
        @NotNull final String sql = String.format("SELECT * FROM %s WHERE email = ? LIMIT 1", getTableName());
        try (@NotNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,email);
            @NotNull final ResultSet rowSet = statement.executeQuery();
            if (!rowSet.next()) return null;
            return fetch(rowSet);
        }
    }


    @Override
    public Boolean isLoginExist(@NotNull final String login) {
        return findByLogin(login) != null;
    }

    @Override
    public Boolean isEmailExist(@NotNull final String email) {
        return findByEmail(email) != null;
    }

}
