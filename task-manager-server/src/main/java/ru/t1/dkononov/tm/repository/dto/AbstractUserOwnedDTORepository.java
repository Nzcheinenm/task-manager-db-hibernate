package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.dto.IUserOwnedDTORepository;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;
import ru.t1.dkononov.tm.dto.model.AbstractUserOwnedModelDTO;

import javax.persistence.EntityManager;
import java.util.Comparator;

public abstract class AbstractUserOwnedDTORepository<E extends AbstractUserOwnedModelDTO> extends AbstractDTORepository<E>
        implements IUserOwnedDTORepository<E> {

    public AbstractUserOwnedDTORepository(@NotNull final EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public String getSortType(@NotNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return "created";
        else if (comparator == StatusComparator.INSTANCE) return "status";
        else return "name";
    }

    @Override
    public void add(@NotNull final String userId, @NotNull final E entity) {
        if (userId.isEmpty()) return;
        entity.setUserId(userId);
        entityManager.persist(entity);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final E entity) {
        if (userId.isEmpty()) return;
        entity.setUserId(userId);
        entityManager.remove(entity);
    }

    @Override
    public void update(@NotNull final String userId, @NotNull E entity) {
        if (userId.isEmpty()) return;
        entity.setUserId(userId);
        entityManager.merge(entity);
    }

}
