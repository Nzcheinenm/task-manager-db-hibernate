package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;

public interface IConnectionService {

    @NotNull
    @SneakyThrows
    SqlSessionFactory getSqlSessionFactory();

    @NotNull SqlSession getSqlSession();

}
