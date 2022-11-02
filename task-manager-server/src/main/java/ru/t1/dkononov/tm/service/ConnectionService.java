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
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;

    public ConnectionService(@NotNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
        this.sqlSessionFactory = getSqlSessionFactory();
        this.entityManagerFactory = factory();
    }

    @NotNull
    @SneakyThrows
    private EntityManagerFactory factory() {
        final Map<String,String> settings = new HashMap<>();
        settings.put(org.hibernate.cfg.Environment.DRIVER,propertyService.getDatabaseDriver());
        settings.put(org.hibernate.cfg.Environment.URL,propertyService.getDatabaseUrl());
        settings.put(org.hibernate.cfg.Environment.USER,propertyService.getDatabaseUser());
        settings.put(org.hibernate.cfg.Environment.PASS,propertyService.getDatabasePassword());
        settings.put(org.hibernate.cfg.Environment.DIALECT,propertyService.getDatabaseDialect());
        settings.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO,propertyService.getDatabaseHbm2auto());
        settings.put(org.hibernate.cfg.Environment.SHOW_SQL,propertyService.getDatabaseShowSql());
        @NotNull final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        @NotNull final StandardServiceRegistry registry = registryBuilder.build();
        @NotNull final MetadataSources source = new MetadataSources(registry);
        source.addAnnotatedClass(ProjectDTO.class);
        source.addAnnotatedClass(TaskDTO.class);
        source.addAnnotatedClass(UserDTO.class);
        source.addAnnotatedClass(SessionDTO.class);
        @NotNull final Metadata metadata = source.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
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
