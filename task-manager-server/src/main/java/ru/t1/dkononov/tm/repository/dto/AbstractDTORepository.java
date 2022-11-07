package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.dto.IDTORepository;
import ru.t1.dkononov.tm.dto.model.AbstractModelDTO;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDTORepository<E extends AbstractModelDTO> implements IDTORepository<E> {

    @NotNull
    protected final EntityManager entityManager;

    public AbstractDTORepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(@NotNull E entity) {
        entityManager.persist(entity);
    }

    @Override
    public abstract void clear();

    @NotNull
    @Override
    public abstract List<E> findAll();

    @NotNull
    @Override
    public abstract E findById(@NotNull String id);

    @Override
    public void remove(@NotNull final E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void update(@NotNull E entity) {
        entityManager.merge(entity);
    }

}
