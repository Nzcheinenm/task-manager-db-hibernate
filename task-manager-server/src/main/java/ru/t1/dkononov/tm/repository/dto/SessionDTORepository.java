package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.ISessionDTORepository;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class SessionDTORepository extends AbstractUserOwnedDTORepository<SessionDTO> implements ISessionDTORepository {

    public SessionDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM SessionDTO";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM SessionDTO m WHERE m.userId = :userId";
        entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<SessionDTO> findAll() {
        @NotNull final String sql = "SELECT m FROM SessionDTO m";
        return entityManager.createQuery(sql, SessionDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<SessionDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM SessionDTO m WHERE m.userId = :userId";
        return entityManager.createQuery(sql, SessionDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<SessionDTO> findAll(@NotNull Sort sort) {
        @NotNull final String sql = "SELECT m FROM SessionDTO m ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, SessionDTO.class)
                .getResultList();
    }

    @NotNull
    @Override
    public List<SessionDTO> findAll(@NotNull final String userId, @NotNull Sort sort) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM SessionDTO m WHERE m.user.id = :userId ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, SessionDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    @NotNull
    @Override
    public SessionDTO findById(@NotNull final String id) {
        return entityManager.find(SessionDTO.class, id);
    }

    @Override
    public SessionDTO findByIndex(@Nullable Integer index) {
        @NotNull final String sql = "SELECT m FROM SessionDTO m";
        return Objects.requireNonNull(entityManager.createQuery(sql, SessionDTO.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public SessionDTO findByIndex(@Nullable String userId, @Nullable Integer index) {
        @NotNull final String sql = "SELECT m FROM SessionDTO m WHERE m.user.id = :userId";
        return Objects.requireNonNull(entityManager.createQuery(sql, SessionDTO.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Nullable
    @Override
    public SessionDTO findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM SessionDTO m WHERE m.userId = :userId AND m.id = :id";
        return entityManager.createQuery(sql, SessionDTO.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void removeById(@NotNull final String id) {
        @NotNull final Optional<SessionDTO> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        @NotNull final Optional<SessionDTO> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }

}
