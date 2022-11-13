package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.marker.DataCategory;
import ru.t1.dkononov.tm.repository.model.TaskRepository;
import ru.t1.dkononov.tm.service.ConnectionService;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(DataCategory.class)
public class TaskRepositoryTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final TaskRepository repository = new TaskRepository(connectionService.getEntityManager());

    @Before
    public void before() {
        repository.add(USER_TASK);
        repository.add(ADMIN_TASK);
    }

    @After
    public void after() {
        repository.add(USER_TASK2);
        repository.clear();
    }

    @Test
    public void addByUserId() {
        repository.add(USER1.getId(), USER_TASK2);
        Assert.assertNotNull(repository.findById(USER_TASK2.getId()));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(ADMIN_TASK.getUser().getId(), USER2.getId());
    }

    @Test
    public void findAllIfOne() {
        @NotNull final TaskRepository emptyRepository = new TaskRepository(connectionService.getEntityManager());
        Assert.assertTrue(emptyRepository.findAll().isEmpty());
        emptyRepository.add(USER_TASK);
        Assert.assertEquals(USER_TASK, emptyRepository.findById(USER_TASK.getId()));
    }

    @Test
    public void findById() {
        Assert.assertNotNull(repository.findById(USER_TASK.getId()));
    }

    @Test
    public void removeAll() {
        repository.clear();
        Assert.assertTrue(repository.findAll().isEmpty());
    }

}
