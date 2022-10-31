package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.dto.model.SessionDTO;

import static ru.t1.dkononov.tm.constant.TestData.SESSION;
import static ru.t1.dkononov.tm.constant.TestData.USER1;

@Category(UnitCategory.class)
public class SessionServiceTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final SessionService service = new SessionService(connectionService);

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        service.add(SESSION);
    }

    @After
    public void after() throws UserIdEmptyException {
        service.clear(USER1.getId());
    }

    @Test
    public void add() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(SESSION));
        Assert.assertThrows(Exception.class, () -> service.add((SessionDTO) null));
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER1.getId(), SESSION));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(SESSION.getUserId(), USER1.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final SessionService SessionService = new SessionService(connectionService);
        Assert.assertTrue(SessionService.findAll().isEmpty());
    }

    @Test
    public void updateByNullId() {
        Assert.assertThrows(Exception.class, () -> service.findById(USER1.getId(), null));
    }

}
