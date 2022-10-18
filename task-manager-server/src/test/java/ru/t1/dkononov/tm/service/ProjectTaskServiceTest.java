package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;


import java.util.Objects;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(UnitCategory.class)
public class ProjectTaskServiceTest {

    @Nullable
    private final IProjectRepository projectRepository = new ProjectRepository();

    @Nullable
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectRepository,taskRepository);

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        projectRepository.add(USER1.getId(),USER_PROJECT);
        projectRepository.add(USER1.getId(),USER_PROJECT2);

        taskRepository.add(USER1.getId(),USER_TASK);
        taskRepository.add(USER1.getId(),USER_TASK2);
    }

    @After
    public void after() throws UserIdEmptyException {
        projectRepository.clear(USER1.getId());
        taskRepository.clear(USER1.getId());
    }

    @Test
    public void bindTaskToProject() throws AbstractException {
        projectTaskService.bindTaskToProject(USER1.getId(),USER_PROJECT.getId(),USER_TASK.getId());
        Assert.assertTrue(Objects.equals(USER_TASK.getProjectId(), USER_PROJECT.getId()));
    }

    @Test
    public void removeProjectById() throws AbstractException {
        projectTaskService.removeProjectById(USER1.getId(),USER_PROJECT.getId());
        Assert.assertFalse(projectRepository.existsById(USER_PROJECT.getId()));
    }

    @Test
    public void unbindTaskFromProject() throws AbstractException {
        projectTaskService.unbindTaskFromProject(USER1.getId(),USER_PROJECT.getId(),USER_TASK.getId());
        Assert.assertNull(USER_TASK.getProjectId());
    }

}
