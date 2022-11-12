package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;
import ru.t1.dkononov.tm.model.AbstractUserOwnedModel;

import javax.persistence.EntityManager;
import java.util.Comparator;

public abstract class AbstractUserOwnedRepository<E extends AbstractUserOwnedModel> extends AbstractRepository<E> implements IUserOwnedRepository<E> {

    public AbstractUserOwnedRepository(@NotNull final EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    protected String getSortType(@NotNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return "created";
        else if (comparator == StatusComparator.INSTANCE) return "status";
        else return "name";
    }

    @Override
    public void add(@NotNull final String userId, @NotNull final E entity) {
        if (userId.isEmpty()) return;
        entityManager.persist(entity);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final E entity) {
        if (userId.isEmpty()) return;
        entityManager.remove(entity);
    }

    @Override
    public void update(@NotNull final String userId, @NotNull E entity) {
        if (userId.isEmpty()) return;
        entityManager.merge(entity);
    }

}