package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IDatabaseProperty;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionService implements IConnectionService {

    @NotNull final IDatabaseProperty databaseProperty;

    public ConnectionService(@NotNull final IDatabaseProperty databaseProperty) {
        this.databaseProperty = databaseProperty;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Connection getConnection() {
        @NotNull final String username = databaseProperty.getDatabaseUser();
        @NotNull final String password = databaseProperty.getDatabasePassword();
        @NotNull final String url = databaseProperty.getDatabaseUrl();
        @NotNull final Connection connection = DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(false);
        return connection;
    }

}
