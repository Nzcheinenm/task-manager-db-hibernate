package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.LoginEmptyException;
import ru.t1.dkononov.tm.marker.DataCategory;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.model.ProjectRepository;
import ru.t1.dkononov.tm.repository.model.UserRepository;
import ru.t1.dkononov.tm.service.ConnectionService;
import ru.t1.dkononov.tm.service.PropertyService;
import ru.t1.dkononov.tm.util.HashUtil;

import static ru.t1.dkononov.tm.constant.TestData.*;


@Category(DataCategory.class)
public class UserRepositoryTest {

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final PropertyService propertyService = new PropertyService();

    @NotNull
    private final ProjectRepository projectRepository = new ProjectRepository(connectionService.getEntityManager());


    @NotNull
    private final UserRepository repository = new UserRepository(connectionService.getEntityManager());

    @NotNull
    private static final String LOGIN_TEST = "logintest";

    @NotNull
    private static final String PASS_TEST = "logintest";

    @NotNull
    private static final String PASS_RETEST = "logintest";

    @NotNull
    private static final String NAME = "firstName";

    @NotNull
    private UserDTO userTesting;

    @Before
    public void before() throws AbstractException {
        @NotNull final User user = new User();
        user.setLogin(LOGIN_TEST);
        user.setPasswordHash(HashUtil.salt(propertyService, PASS_TEST));
        user.setRole(Role.USUAL);
        repository.add(user);
    }

    @After
    public void after() {
        repository.clear();
    }

    @Test
    public void create() throws AbstractException {
        @NotNull final User user1 = new User();
        user1.setLogin(LOGIN);
        user1.setPasswordHash(HashUtil.salt(propertyService, PASSWORD));
        user1.setRole(Role.USUAL);
        repository.add(user1);
        @Nullable final User user = repository.findByLogin(LOGIN);
        Assert.assertNotNull(user);
        projectRepository.add(user.getId(), USER_PROJECT);
        Assert.assertNotNull(projectRepository.findById(USER_PROJECT.getId()));
    }

    @Test
    public void findByLogin() throws LoginEmptyException {
        Assert.assertNotNull(repository.findByLogin(LOGIN_TEST));
    }


    @Test
    public void removeById() throws AbstractFieldException {
        repository.removeById(userTesting.getId());
        Assert.assertNull(repository.findByLogin(LOGIN_TEST));
    }

}
