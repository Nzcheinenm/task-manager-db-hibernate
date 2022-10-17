package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.marker.DataCategory;

import static ru.t1.dkononov.tm.constant.TestData.*;
import static ru.t1.dkononov.tm.constant.TestData.ADMIN_TASK;

@Category(DataCategory.class)
public class TaskRepositoryTest {

    @NotNull
    private final TaskRepository repository = new TaskRepository();

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
    public void add() {
        Assert.assertNotNull(repository.add(USER_TASK2));
        Assert.assertThrows(Exception.class,() -> repository.add(NULL_TASK));
    }

    @Test
    public void addByUserId() {
        Assert.assertNotNull(repository.add(USER1.getId(), USER_TASK2));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(ADMIN_TASK.getUserId(),USER2.getId());
    }

    @Test
    public void findAllIfOne() {
        @NotNull final TaskRepository emptyRepository = new TaskRepository();
        Assert.assertTrue(emptyRepository.findAll().isEmpty());
        emptyRepository.add(USER_TASK);
        Assert.assertEquals(USER_TASK,emptyRepository.findById(USER_TASK.getId()));
    }

    @Test
    public void findById() {
        Assert.assertNotNull(repository.findById(USER_TASK.getId()));
    }

    @Test
    public void removeById() {
        Assert.assertNotNull(repository.removeById(ADMIN_TASK.getId()));
    }

    @Test
    public void removeByIndex() {
        Assert.assertNotNull(repository.removeByIndex(1));
    }

    @Test
    public void removeAll() {
        repository.clear();
        Assert.assertTrue(repository.findAll().isEmpty());
    }

}
