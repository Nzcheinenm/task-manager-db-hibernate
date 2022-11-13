package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.repository.model.ISessionRepository;
import ru.t1.dkononov.tm.api.repository.model.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.marker.DataCategory;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.repository.model.SessionRepository;
import ru.t1.dkononov.tm.service.ConnectionService;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestData.SESSION;
import static ru.t1.dkononov.tm.constant.TestData.USER1;

@Category(DataCategory.class)
public class SessionRepositoryTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final IUserOwnedRepository<Session> repository = new SessionRepository(connectionService.getEntityManager());

    @Before
    public void before() throws UserIdEmptyException, ProjectNotFoundException {
        repository.add(USER1.getId(), SESSION);
    }

    @After
    public void after() throws UserIdEmptyException {
        repository.clear(USER1.getId());
    }

    @Test
    public void addByUserId() throws UserIdEmptyException, ProjectNotFoundException {
        repository.add(USER1.getId(), SESSION);
        Assert.assertNotNull(repository.findById(SESSION.getId()));
    }

    @Test
    public void createByUserId() {
        Assert.assertEquals(SESSION.getUser().getId(), USER1.getId());
    }

    @Test
    public void findAllNull() {
        @NotNull final SessionRepository sessionRepository = new SessionRepository(connectionService.getEntityManager());
        Assert.assertTrue(sessionRepository.findAll().isEmpty());
    }

    @Test
    public void findByNullId() {
        Assert.assertNull(repository.findById(USER1.getId(), null));
    }

}
