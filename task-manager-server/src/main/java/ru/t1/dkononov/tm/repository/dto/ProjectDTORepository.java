package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IProjectDTORepository;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import javax.persistence.EntityManager;
import java.util.*;

public final class ProjectDTORepository extends AbstractUserOwnedDTORepository<ProjectDTO> implements IProjectDTORepository {

    public ProjectDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM ProjectDTO";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM ProjectDTO m WHERE m.userId = :userId";
        entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll() {
        @NotNull final String sql = "SELECT m FROM ProjectDTO m";
        return entityManager.createQuery(sql, ProjectDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.userId = :userId";
        return entityManager.createQuery(sql, ProjectDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll(@NotNull Sort sort) {
        @NotNull final String sql = "SELECT m FROM ProjectDTO m ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, ProjectDTO.class)
                .getResultList();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll(@NotNull final String userId, @NotNull Sort sort) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.userId = :userId ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, ProjectDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAll(@NotNull final String userId, @NotNull final Comparator<ProjectDTO> comparator) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.userId = :userId" + getSortType(comparator);
        return entityManager.createQuery(sql, ProjectDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public ProjectDTO findById(@NotNull final String id) {
        return entityManager.find(ProjectDTO.class, id);
    }

    @Override
    public ProjectDTO findByIndex(@Nullable String userId, @Nullable Integer index) {
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.user.id = :userId";
        return Objects.requireNonNull(entityManager.createQuery(sql, ProjectDTO.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Nullable
    @Override
    public ProjectDTO findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM ProjectDTO m WHERE m.userId = :userId AND m.id = :id";
        return entityManager.createQuery(sql, ProjectDTO.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @NotNull
    @Override
    public ProjectDTO findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM ProjectDTO m";
        return Objects.requireNonNull(entityManager.createQuery(sql, ProjectDTO.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        @NotNull final Optional<ProjectDTO> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        @NotNull final Optional<ProjectDTO> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }

}
