package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;

public interface IConnectionService {

    @NotNull Connection getConnection();

}
