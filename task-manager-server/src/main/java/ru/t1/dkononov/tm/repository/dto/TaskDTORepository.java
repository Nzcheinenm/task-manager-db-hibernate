package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.ITaskDTORepository;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import javax.persistence.EntityManager;
import java.util.*;

public final class TaskDTORepository extends AbstractUserOwnedDTORepository<TaskDTO> implements ITaskDTORepository {

    public TaskDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM TaskDTO";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM TaskDTO m WHERE m.userId = :userId";
        entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAll() {
        @NotNull final String sql = "SELECT m FROM TaskDTO m";
        return entityManager.createQuery(sql, TaskDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId";
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAll(@NotNull final String userId, @NotNull Sort sort) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAll(@NotNull final String userId, @NotNull final Comparator<TaskDTO> comparator) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId" + getSortType(comparator);
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public TaskDTO findById(@NotNull final String id) {
        return entityManager.find(TaskDTO.class, id);
    }

    @Override
    public TaskDTO findByIndex(@Nullable String userId, @Nullable Integer index) {
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.user.id = :userId";
        return Objects.requireNonNull(entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Nullable
    @Override
    public TaskDTO findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId AND m.id = :id";
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @NotNull
    @Override
    public TaskDTO findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM TaskDTO m";
        return Objects.requireNonNull(entityManager.createQuery(sql, TaskDTO.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        Optional<TaskDTO> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        Optional<TaskDTO> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }


    @NotNull
    @Override
    public List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId AND m.projectId = :projectId";
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Nullable
    @Override
    public TaskDTO findTaskIdByProjectId(@NotNull String userId, @NotNull String projectId, @NotNull String taskId) {
        if (userId.isEmpty() || taskId.isEmpty() || projectId.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM TaskDTO m WHERE m.userId = :userId AND m.id = :id AND m.projectId = :projectId";
        return entityManager.createQuery(sql, TaskDTO.class)
                .setParameter("taskId", taskId)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

}
