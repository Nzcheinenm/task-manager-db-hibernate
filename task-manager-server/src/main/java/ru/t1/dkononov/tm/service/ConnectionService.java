package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;

    public ConnectionService(@NotNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
        this.entityManagerFactory = factory();
    }

    @NotNull
    @SneakyThrows
    private EntityManagerFactory factory() {
        final Map<String, String> settings = new HashMap<>();
        settings.put(org.hibernate.cfg.Environment.DRIVER, propertyService.getDatabaseDriver());
        settings.put(org.hibernate.cfg.Environment.URL, propertyService.getDatabaseUrl());
        settings.put(org.hibernate.cfg.Environment.USER, propertyService.getDatabaseUser());
        settings.put(org.hibernate.cfg.Environment.PASS, propertyService.getDatabasePassword());
        settings.put(org.hibernate.cfg.Environment.DIALECT, propertyService.getDatabaseDialect());
        settings.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, propertyService.getDatabaseHbm2auto());
        settings.put(org.hibernate.cfg.Environment.SHOW_SQL, propertyService.getDatabaseShowSql());
        @NotNull final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        @NotNull final StandardServiceRegistry registry = registryBuilder.build();
        @NotNull final MetadataSources source = new MetadataSources(registry);
        source.addAnnotatedClass(ProjectDTO.class);
        source.addAnnotatedClass(TaskDTO.class);
        source.addAnnotatedClass(UserDTO.class);
        source.addAnnotatedClass(SessionDTO.class);
        source.addAnnotatedClass(Project.class);
        source.addAnnotatedClass(Task.class);
        source.addAnnotatedClass(User.class);
        source.addAnnotatedClass(Session.class);
        @NotNull final Metadata metadata = source.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    @NotNull
    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
