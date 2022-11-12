package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.ITaskRepository;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.Task;

import javax.persistence.EntityManager;
import java.util.*;

public final class TaskRepository extends AbstractUserOwnedRepository<Task> implements ITaskRepository {

    public TaskRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM Task";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM Task m WHERE m.user.id = :userId";
        entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<Task> findAll() {
        @NotNull final String sql = "SELECT m FROM Task m";
        return entityManager.createQuery(sql, Task.class).getResultList();
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Task m WHERE m.user.id = :userId";
        return entityManager.createQuery(sql, Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Task findByIndex(@NotNull String userId, @NotNull Integer index) {
        @NotNull final String sql = "SELECT m FROM Task m WHERE m.user.id = :userId";
        return Objects.requireNonNull(entityManager.createQuery(sql, Task.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId, @NotNull Sort sort) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.user.id = :userId ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId, @NotNull final Comparator<Task> comparator) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Task m WHERE m.user.id = :userId" + getSortType(comparator);
        return entityManager.createQuery(sql, Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Task findById(@NotNull final String id) {
        return entityManager.find(Task.class, id);
    }

    @Nullable
    @Override
    public Task findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM Task m WHERE m.user.id = :userId AND m.id = :id";
        return entityManager.createQuery(sql, Task.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @NotNull
    @Override
    public Task findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM Task m";
        return Objects.requireNonNull(entityManager.createQuery(sql, Task.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        Optional<Task> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        Optional<Task> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }


    @NotNull
    @Override
    public List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Task m WHERE m.user.id = :userId AND m.projectId = :projectId";
        return entityManager.createQuery(sql, Task.class)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Nullable
    @Override
    public Task findTaskIdByProjectId(@NotNull String userId, @NotNull String projectId, @NotNull String taskId) {
        if (userId.isEmpty() || taskId.isEmpty() || projectId.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.user.id = :userId AND m.id = :taskId AND m.projectId = :projectId";
        return entityManager.createQuery(sql, Task.class)
                .setParameter("taskId", taskId)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

}