package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.IUserRepository;
import ru.t1.dkononov.tm.model.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM User";
        entityManager.createQuery(sql).executeUpdate();
    }

    @NotNull
    @Override
    public List<User> findAll() {
        @NotNull final String sql = "SELECT m FROM User m";
        return entityManager.createQuery(sql, User.class).getResultList();
    }

    @NotNull
    @Override
    public User findById(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }


    @NotNull
    @Override
    public User findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM User m ";
        return Objects.requireNonNull(entityManager.createQuery(sql, User.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        Optional<User> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    @Nullable
    public User findByLogin(@NotNull String login) {
        if (login.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM User m WHERE m.login = :login";
        return entityManager.createQuery(sql, User.class)
                .setParameter("login", login)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Nullable
    public User findByEmail(@NotNull String email) {
        if (email.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM User m WHERE m.email = :email";
        return entityManager.createQuery(sql, User.class)
                .setParameter("email", email)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

}
