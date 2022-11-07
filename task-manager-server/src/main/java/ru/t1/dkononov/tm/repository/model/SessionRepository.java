package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.ISessionDTORepository;
import ru.t1.dkononov.tm.api.repository.model.ISessionRepository;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.repository.dto.AbstractUserOwnedDTORepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SessionRepository extends AbstractUserOwnedRepository<Session> implements ISessionRepository {

    public SessionRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM Session";
        entityManager.createQuery(sql).executeUpdate();
    }

    @Override
    public void clear(@NotNull String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String sql = "DELETE FROM Session m WHERE m.user.id = :userId";
        entityManager.createQuery(sql)
                .setParameter("user.id",userId)
                .executeUpdate();
    }

    @NotNull
    @Override
    public List<Session> findAll() {
        @NotNull final String sql = "SELECT m FROM Session m";
        return entityManager.createQuery(sql, Session.class).getResultList();
    }

    @NotNull
    @Override
    public List<Session> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final String sql = "SELECT m FROM Session m WHERE m.user.id = :userId";
        return entityManager.createQuery(sql, Session.class)
                .setParameter("user.id", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Session findById(@NotNull final String id) {
        return entityManager.find(Session.class,id);
    }

    @Nullable
    @Override
    public Session findById(@NotNull final String userId, @NotNull final String id) {
        if (userId.isEmpty() || id.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM Session m WHERE m.user.id = :userId AND m.id = :id";
        return entityManager.createQuery(sql, Session.class)
                .setParameter("id",id)
                .setParameter("user.id",userId)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void removeById(@NotNull final String id) {
        Optional<Session> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        Optional<Session> entity = Optional.ofNullable(findById(userId, id));
        entity.ifPresent(this::remove);
    }

}
