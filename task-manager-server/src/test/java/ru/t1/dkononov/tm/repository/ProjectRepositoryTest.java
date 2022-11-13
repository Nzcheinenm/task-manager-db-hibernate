package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.marker.DataCategory;
import ru.t1.dkononov.tm.repository.model.ProjectRepository;
import ru.t1.dkononov.tm.service.ConnectionService;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(DataCategory.class)
public class ProjectRepositoryTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final ProjectRepository repository = new ProjectRepository(connectionService.getEntityManager());

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
    public void createByUserId() {
        Assert.assertEquals(ADMIN_PROJECT.getUser().getId(), USER2.getId());
    }

    @Test
    public void findAll() {
        @NotNull final ProjectRepository emptyRepository = new ProjectRepository(connectionService.getEntityManager());
        Assert.assertTrue(emptyRepository.findAll().isEmpty());
        emptyRepository.add(USER_PROJECT);
        Assert.assertEquals(USER_PROJECT, emptyRepository.findById(USER_PROJECT.getId()));
    }

    @Test
    public void findById() {
        Assert.assertNotNull(repository.findById(USER1.getId(), USER_PROJECT.getId()));
    }

    @Test
    public void removeAll() {
        repository.clear();
        Assert.assertTrue(repository.findAll().isEmpty());
    }

}
