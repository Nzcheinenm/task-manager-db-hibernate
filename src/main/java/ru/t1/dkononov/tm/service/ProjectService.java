package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;

public final class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {


    public ProjectService(final IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(name, description);
    }

    @Override
    public Project create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(name);
    }

    @Override
    public void updateById(final String id, final String name, final String description)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Project project = repository.findById(id);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
    }

    @Override
    public void updateByIndex(final Integer index, final String name, final String description)
            throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Project project = repository.findByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
    }

    @Override
    public void changeProjectStatusById(final String id, final Status status)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final Project project = findById(id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
    }

    @Override
    public void changeProjectStatusByIndex(final Integer index, final Status status)
            throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Project project = findByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
    }

}
