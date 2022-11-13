package ru.t1.dkononov.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.dto.IDTORepository;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;
import ru.t1.dkononov.tm.dto.model.AbstractModelDTO;

import javax.persistence.EntityManager;
import java.util.Comparator;
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

    @NotNull
    @Override
    public String getSortType(@NotNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return "created";
        else if (comparator == StatusComparator.INSTANCE) return "status";
        else return "name";
    }

}
