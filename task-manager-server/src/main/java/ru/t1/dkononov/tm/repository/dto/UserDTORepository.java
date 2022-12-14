package ru.t1.dkononov.tm.repository.dto;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IUserDTORepository;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class UserDTORepository extends AbstractDTORepository<UserDTO> implements IUserDTORepository {

    public UserDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clear() {
        @NotNull final String sql = "DELETE FROM ProjectDTO";
        entityManager.createQuery(sql).executeUpdate();
    }

    @NotNull
    @Override
    public List<UserDTO> findAll() {
        @NotNull final String sql = "SELECT m FROM UserDTO m";
        return entityManager.createQuery(sql, UserDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<UserDTO> findAll(@NotNull Sort sort) {
        @NotNull final String sql = "SELECT m FROM UserDTO m ORDER BY m."
                + getSortType(sort.getComparator());
        return entityManager.createQuery(sql, UserDTO.class)
                .getResultList();
    }

    @NotNull
    @Override
    public UserDTO findById(@NotNull final String id) {
        return entityManager.find(UserDTO.class, id);
    }


    @NotNull
    @Override
    public UserDTO findByIndex(@NotNull final Integer index) {
        @NotNull final String sql = "SELECT m FROM UserDTO m";
        return Objects.requireNonNull(entityManager.createQuery(sql, UserDTO.class)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public void removeById(@NotNull final String id) {
        @NotNull final Optional<UserDTO> entity = Optional.ofNullable(findById(id));
        entity.ifPresent(this::remove);
    }

    @Override
    @Nullable
    public UserDTO findByLogin(@NotNull String login) {
        if (login.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM UserDTO m WHERE m.login = :login";
        return entityManager.createQuery(sql, UserDTO.class)
                .setParameter("login", login)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Nullable
    public UserDTO findByEmail(@NotNull String email) {
        if (email.isEmpty()) return null;
        @NotNull final String sql = "SELECT m FROM UserDTO m WHERE m.email = :email";
        return entityManager.createQuery(sql, UserDTO.class)
                .setParameter("email", email)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .setMaxResults(1).getResultList().stream().findFirst().orElse(null);
    }

}
