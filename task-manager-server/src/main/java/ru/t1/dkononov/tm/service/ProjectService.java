package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;


public final class ProjectService extends AbstractUserOwnedService<Project, IProjectRepository> implements IProjectService {


    public ProjectService(@NotNull final IProjectRepository repository) {
        super(repository);
    }

    @NotNull
    @Override
    public Project create(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(userId, name, description);
    }

    @NotNull
    @Override
    public Project create(@Nullable final String userId, @Nullable final String name)
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @Override
    @Nullable
    public Project updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @Nullable final Project project = repository.findById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        return project;
    }

    @Override
    @Nullable
    public Project updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @Nullable final Project project = repository.findByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    @Nullable
    public Project changeProjectStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @Nullable final Project project = findById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        project.setUserId(userId);
        return project;
    }

    @Override
    public @Nullable Project changeProjectStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @Nullable final Project project = findByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        project.setUserId(userId);
        return project;
    }

}
