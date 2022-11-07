package ru.t1.dkononov.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.AbstractModel;
import javax.persistence.EntityManager;


public abstract class AbstractRepository<E extends AbstractModel> implements ru.t1.dkononov.tm.api.repository.model.IAbstractRepository<E> {

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

}
