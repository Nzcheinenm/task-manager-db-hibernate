package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.repository.model.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.model.ITaskRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.repository.model.ProjectRepository;
import ru.t1.dkononov.tm.repository.model.TaskRepository;
import ru.t1.dkononov.tm.service.model.ProjectService;
import ru.t1.dkononov.tm.service.model.ProjectTaskService;
import ru.t1.dkononov.tm.service.model.TaskService;

import java.util.Objects;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(UnitCategory.class)
public class ProjectTaskServiceTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @Nullable
    private final ProjectRepository projectRepository = new ProjectRepository(connectionService.getEntityManager());

    @Nullable
    private final TaskRepository taskRepository = new TaskRepository(connectionService.getEntityManager());

    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

    @NotNull
    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectService, taskService);

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        projectRepository.add(USER1.getId(), USER_PROJECT);
        projectRepository.add(USER1.getId(), USER_PROJECT2);

        taskRepository.add(USER1.getId(), USER_TASK);
        taskRepository.add(USER1.getId(), USER_TASK2);
    }

    @After
    public void after() throws UserIdEmptyException {
        projectRepository.clear(USER1.getId());
        taskRepository.clear(USER1.getId());
    }

    @Test
    public void bindTaskToProject() throws Exception {
        projectTaskService.bindTaskToProject(USER1.getId(), USER_PROJECT.getId(), USER_TASK.getId());
        Assert.assertTrue(Objects.equals(USER_TASK.getProject().getId(), USER_PROJECT.getId()));
    }

    @Test
    public void removeProjectById() throws Exception {
        projectTaskService.removeProjectById(USER1.getId(), USER_PROJECT.getId());
        Assert.assertFalse(projectRepository.findById(USER_PROJECT.getId()) != null);
    }

    @Test
    public void unbindTaskFromProject() throws Exception {
        projectTaskService.unbindTaskFromProject(USER1.getId(), USER_PROJECT.getId(), USER_TASK.getId());
        Assert.assertNull(USER_TASK.getProject().getId());
    }

}
