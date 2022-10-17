package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.repository.TaskRepository;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(UnitCategory.class)
public class TaskServiceTest {

    @NotNull
    private final TaskRepository repository = new TaskRepository();

    @NotNull
    private final TaskService service = new TaskService(repository);

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        service.add(USER_TASK);
        service.add(USER_TASK2);
        service.add(ADMIN_TASK);
    }

    @After
    public void after() throws UserIdEmptyException {
        service.clear(USER1.getId());
    }

    @Test
    public void add() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER_TASK2));
        Assert.assertThrows(Exception.class,() -> service.add(NULL_TASK));
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER1.getId(), USER_TASK2));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(ADMIN_TASK.getUserId(),USER2.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final TaskRepository TaskRepository = new TaskRepository();
        @NotNull final TaskService TaskService = new TaskService(TaskRepository);
        Assert.assertTrue(TaskService.findAll().isEmpty());
    }

    @Test
    public void updateByNullId() {
        Assert.assertThrows(Exception.class,() -> service.findById(USER1.getId(),null));
    }

    @Test
    public void updateById() throws AbstractException {
        Assert.assertNotNull(service.updateById(USER1.getId(),USER_TASK.getId(),"1","2"));
    }

    @Test
    public void updateByIndex() throws AbstractException {
        Assert.assertNotNull(service.updateByIndex(USER1.getId(),0,"3","4"));
    }

    @Test
    public void changeStatusById() throws AbstractException {
        Assert.assertNotNull(service.changeTaskStatusById(USER1.getId(),USER_TASK.getId(), Status.IN_PROGRESS));
    }

    @Test
    public void changeStatusByIndex() throws AbstractException {
        Assert.assertNotNull(service.changeTaskStatusByIndex(USER1.getId(),0,Status.IN_PROGRESS));
    }

    @Test
    public void removeStatusById() throws AbstractException {
        Assert.assertNotNull(service.removeById(USER1.getId(),USER_TASK.getId()));
    }

    @Test
    public void removeStatusByIndex() throws AbstractException {
        Assert.assertNotNull(service.removeByIndex(USER1.getId(),0));
    }

    @Test
    public void removeAll() throws UserIdEmptyException {
        service.clear(USER1.getId());
        Assert.assertFalse(service.findAll().isEmpty());
    }

}
