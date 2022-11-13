package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.model.IRepository;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;
import ru.t1.dkononov.tm.model.AbstractModel;

import javax.persistence.EntityManager;
import java.util.Comparator;


public abstract class AbstractRepository<E extends AbstractModel> implements IRepository<E> {

    @NotNull
    protected final EntityManager entityManager;

    public AbstractRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(@NotNull E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(@NotNull final E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void update(@NotNull E entity) {
        entityManager.merge(entity);
    }

    @NotNull
    @Override
    public String getSortType(@NotNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return "created";
        else if (comparator == StatusComparator.INSTANCE) return "status";
        else return "name";
    }

}
