package ru.t1.dkononov.tm.api.services;

public interface ILoggerService {
    void info(String message);

    void debug(String message);

    void command(String message);

    void error(Exception e);
}
