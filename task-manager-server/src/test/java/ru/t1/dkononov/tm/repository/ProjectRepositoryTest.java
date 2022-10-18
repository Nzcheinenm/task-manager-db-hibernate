package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.marker.DataCategory;

import static ru.t1.dkononov.tm.constant.TestData.*;
import static ru.t1.dkononov.tm.constant.TestData.USER_PROJECT;

@Category(DataCategory.class)
public class ProjectRepositoryTest {

    @NotNull
    private final ProjectRepository repository = new ProjectRepository();

    @Before
    public void before() {
        repository.add(USER_PROJECT);
        repository.add(ADMIN_PROJECT);
    }

    @After
    public void after() {
        repository.add(USER_PROJECT2);
        repository.clear();
    }

    @Test
    public void add() {
        Assert.assertNotNull(repository.add(USER_PROJECT2));
        Assert.assertNull(repository.add(NULL_PROJECT));
    }

    @Test
    public void addByUserId() {
        Assert.assertNotNull(repository.add(USER1.getId(), USER_PROJECT2));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(ADMIN_PROJECT.getUserId(),USER2.getId());
    }

    @Test
    public void findAll() {
        @NotNull final ProjectRepository emptyRepository = new ProjectRepository();
        Assert.assertTrue(emptyRepository.findAll().isEmpty());
        emptyRepository.add(USER_PROJECT);
        Assert.assertEquals(USER_PROJECT,emptyRepository.findById(USER_PROJECT.getId()));
    }

    @Test
    public void findById() {
        Assert.assertNotNull(repository.findById(USER1.getId(),USER_PROJECT.getId()));
    }

    @Test
    public void removeById() {
        Assert.assertNotNull(repository.removeById(ADMIN_PROJECT.getId()));
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
