package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.repository.SessionRepository;

import static ru.t1.dkononov.tm.constant.TestData.*;
import static ru.t1.dkononov.tm.constant.TestData.USER1;

@Category(UnitCategory.class)
public class SessionServiceTest {

    @NotNull
    private final SessionRepository repository = new SessionRepository();

    @NotNull
    private final SessionService service = new SessionService(repository);

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
        Assert.assertThrows(Exception.class, () -> service.add((Session) null));
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(service.add(USER1.getId(), SESSION));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(SESSION.getUserId(),USER1.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final SessionRepository SessionRepository = new SessionRepository();
        @NotNull final SessionService SessionService = new SessionService(SessionRepository);
        Assert.assertTrue(SessionService.findAll().isEmpty());
    }

    @Test
    public void updateByNullId() {
        Assert.assertThrows(Exception.class,() -> service.findById(USER1.getId(),null));
    }

}
