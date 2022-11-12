package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.service.model.ProjectService;

import static ru.t1.dkononov.tm.constant.TestData.*;

@Category(UnitCategory.class)
public class ProjectServiceTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final ProjectService service = new ProjectService(connectionService);

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        service.add(USER1.getId(), USER_PROJECT);
        service.add(USER1.getId(), USER_PROJECT2);
        service.add(USER2.getId(), ADMIN_PROJECT);
    }

    @After
    public void after() throws UserIdEmptyException {
        service.clear(USER1.getId());
    }

    @Test
    public void add() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER_PROJECT2));
        Assert.assertThrows(Exception.class, () -> service.add(NULL_PROJECT));
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER1.getId(), USER_PROJECT2));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(ADMIN_PROJECT.getUserId(), USER2.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final ConnectionService connectionService = new ConnectionService(new PropertyService());
        @NotNull final ProjectService projectService = new ProjectService(connectionService);
        Assert.assertTrue(projectService.findAll().isEmpty());
    }

    @Test
    public void findByNullId() {
        Assert.assertThrows(Exception.class, () -> service.findById(USER1.getId(), null));
    }

    @Test
    public void updateById() throws AbstractException {
        Assert.assertNotNull(service.updateById(USER1.getId(), USER_PROJECT.getId(), "1", "2"));
    }

    @Test
    public void updateByIndex() throws AbstractException {
        Assert.assertNotNull(service.updateByIndex(USER1.getId(), 1, "3", "4"));
    }

    @Test
    public void changeStatusById() throws AbstractException {
        Assert.assertNotNull(service.changeProjectStatusById(USER1.getId(), USER_PROJECT.getId(), Status.IN_PROGRESS));
    }

    @Test
    public void changeStatusByIndex() throws AbstractException {
        Assert.assertNotNull(service.changeProjectStatusByIndex(USER1.getId(), 1, Status.IN_PROGRESS));
    }

    @Test
    public void removeStatusById() throws AbstractException {
        Assert.assertNotNull(service.removeById(USER1.getId(), USER_PROJECT.getId()));
    }

    @Test
    public void removeStatusByIndex() throws AbstractException {
        Assert.assertNotNull(service.removeByIndex(USER1.getId(), 1));
    }

    @Test
    public void removeAll() throws UserIdEmptyException {
        service.clear(USER1.getId());
        Assert.assertFalse(service.existsById(USER_PROJECT.getId()));
    }

}
