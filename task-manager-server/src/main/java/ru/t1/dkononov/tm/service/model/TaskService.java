package ru.t1.dkononov.tm.service.model;


import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.repository.model.IUserRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.model.TaskRepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractUserOwnedService<Task, TaskRepository> implements ITaskService {

    public TaskService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    protected @NotNull IUserOwnedRepository getRepository(@NotNull EntityManager entityManager) {
        return new TaskRepository(entityManager);
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Task> findAll(
            @Nullable final Sort sort
    ) {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final ITaskRepository repository = (ITaskRepository) getRepository(entityManager);
            return repository.findAll(sort);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            return repository.findById(userId, id) != null;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final ITaskRepository repository = (ITaskRepository) getRepository(entityManager);
            return repository.findAllByProjectId(userId, projectId);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name, @Nullable final String description)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = new Task();
            task.setUser(entityManager.find(User.class, userId));
            task.setName(name);
            task.setDescription(description);
            repository.add(task);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = new Task();
            task.setUser(entityManager.find(User.class, userId));
            task.setName(name);
            repository.add(task);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = repository.findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = repository.findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task changeTaskStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task changeTaskStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            task = findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUser(entityManager.find(User.class, userId));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    public void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();

        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task task;
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository repository = (ITaskRepository) getRepository(entityManager);
            task = repository.findTaskIdByProjectId(userId, taskId, projectId);
            if (task == null) throw new TaskIdEmptyException();
            task.setProject(entityManager.find(Project.class, projectId));
            ;
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task removeById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final Task result;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            result = repository.findById(userId, id);
            remove(userId, result);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Task findById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserOwnedRepository<Task> repository = getRepository(entityManager);
            return repository.findById(userId, id);
        } finally {
            entityManager.close();
        }
    }

}
