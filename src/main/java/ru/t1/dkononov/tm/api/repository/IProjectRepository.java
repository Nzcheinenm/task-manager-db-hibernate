package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.Project;

public interface IProjectRepository extends IRepository<Project> {

    Project create(final String name);

    Project create(final String name, final String description);


}
