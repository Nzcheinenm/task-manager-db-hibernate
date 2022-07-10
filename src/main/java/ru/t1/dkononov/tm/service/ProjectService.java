package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.AbstractEntityNotFoundException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public final class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findAll(final Comparator<Project> comparator) {
        if (comparator == null) return findAll();
        return projectRepository.findAll(comparator);
    }

    @Override
    public List<Project> findAll(final Sort sort) {
        if (sort == null) return findAll();
        return findAll(sort.getComparator());
    }

    @Override
    public Project add(final Project project) throws AbstractEntityNotFoundException {
        if (project == null) throw new ProjectNotFoundException();
        return projectRepository.add(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

    @Override
    public Project create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return projectRepository.create(name, description);
    }

    @Override
    public Project create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return projectRepository.create(name);
    }

    @Override
    public Project findById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return projectRepository.findById(id);
    }

    @Override
    public Project findByIndex(final Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return projectRepository.findByIndex(index);
    }

    @Override
    public void remove(final Project project) {
        projectRepository.remove(project);
    }

    @Override
    public Project removeById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final Project project = projectRepository.findById(id);
        remove(project);
        return project;
    }

    @Override
    public Project removeByIndex(final Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Project project = projectRepository.findByIndex(index);
        projectRepository.remove(project);
        return project;
    }

    @Override
    public void updateById(final String id, final String name, final String description)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Project project = projectRepository.findById(id);
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
        final Project project = projectRepository.findByIndex(index);
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
