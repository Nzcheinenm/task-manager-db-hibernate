package ru.t1.dkononov.tm.repository.model;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.IProjectRepository;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.Project;

import javax.persistence.EntityManager;
import java.util.*;

public final class ProjectRepository extends AbstractUserOwnedRepository<Project> implements IProjectRepository {

    public ProjectRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM Project";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM Project m WHERE m.user.id = :userId";
        entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<Project> findAll() {
        @NotNull final String sql = "SELECT m FROM Project m";
        return entityManager.createQuery(sql, Project.class).getResultList();
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Project m WHERE m.user.id = :userId";
        return entityManager.createQuery(sql, Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Project findByIndex(@NotNull String userId, @NotNull Integer index) {
        @NotNull final String sql = "SELECT m FROM Project m WHERE m.user.id = :userId ";
        return Objects.requireNonNull(entityManager.createQuery(sql, Project.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final String userId, @NotNull Sort sort) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.user.id = :userId ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final String userId, @NotNull final Comparator<ProjectDTO> comparator) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Project m WHERE m.user.id = :userId" + getSortType(comparator);
        return entityManager.createQuery(sql, Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Project findById(@NotNull final String id) {
        return entityManager.find(Project.class, id);
    }

    @Nullable
    @Override
    public Project findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM Project m WHERE m.user.id = :userId AND m.id = :id";
        return entityManager.createQuery(sql, Project.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @NotNull
    @Override
    public Project findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM Project m";
        return Objects.requireNonNull(entityManager.createQuery(sql, Project.class)
                .setParameter("index", index)
                .setMaxResults(1)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        Optional<Project> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        Optional<Project> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }

}
