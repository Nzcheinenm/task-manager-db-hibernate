package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.DataCategory;
import ru.t1.dkononov.tm.service.ConnectionService;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestData.SESSION;
import static ru.t1.dkononov.tm.constant.TestData.USER1;

@Category(DataCategory.class)
public class SessionRepositoryTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final ISessionRepository repository = new SessionRepository(connectionService.getSqlSession());

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        repository.add(USER1.getId(), SESSION);
    }

    @After
    public void after() throws UserIdEmptyException {
        repository.clear(USER1.getId());
    }

    @Test
    public void add() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(repository.add(SESSION));
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        Assert.assertNotNull(repository.add(USER1.getId(), SESSION));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(SESSION.getUserId(), USER1.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final SessionRepository sessionRepository = new SessionRepository(connectionService.getSqlSession());
        Assert.assertTrue(sessionRepository.findAll().isEmpty());
    }

    @Test
    public void findByNullId() {
        Assert.assertNull(repository.findById(USER1.getId(), null));
    }

}
