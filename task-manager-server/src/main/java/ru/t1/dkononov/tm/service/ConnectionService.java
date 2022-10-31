package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;

import javax.sql.DataSource;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public ConnectionService(@NotNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
        this.sqlSessionFactory = getSqlSessionFactory();
    }

    @NotNull
    @Override
    @SneakyThrows
    public SqlSessionFactory getSqlSessionFactory() {
        @NotNull final String username = propertyService.getDatabaseUser();
        @NotNull final String password = propertyService.getDatabasePassword();
        @NotNull final String url = propertyService.getDatabaseUrl();
        @NotNull final String driver = propertyService.getDatabaseDriver();
        @NotNull final DataSource dataSource = new PooledDataSource(driver, url, username, password);
        @NotNull final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        @NotNull final Environment environment = new Environment("tm", transactionFactory, dataSource);
        @NotNull final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IProjectRepository.class);
        configuration.addMapper(ITaskRepository.class);
        configuration.addMapper(ISessionRepository.class);
        configuration.addMapper(IUserRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);

    }

    @Override
    @NotNull
    @SneakyThrows
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
