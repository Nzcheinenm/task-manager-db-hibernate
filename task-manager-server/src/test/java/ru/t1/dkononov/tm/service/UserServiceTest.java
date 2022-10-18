package ru.t1.dkononov.tm.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.LoginEmptyException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;

import java.util.Objects;

import static ru.t1.dkononov.tm.constant.TestData.*;


@Category(UnitCategory.class)
public class UserServiceTest {

    @NotNull
    private final UserRepository repository = new UserRepository();

    @NotNull
    private final ProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final TaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final PropertyService propertyService = new PropertyService();

    @NotNull
    private final UserService service = new UserService(repository,projectRepository,taskRepository,propertyService);

    @NotNull
    private static final String LOGIN_TEST = "logintest";

    @NotNull
    private static final String PASS_TEST = "logintest";

    @NotNull
    private static final String PASS_RETEST = "logintest";

    @NotNull
    private static final String NAME = "firstName";

    @NotNull
    private User userTesting;

    @Before
    public void before() throws AbstractException {
        userTesting = service.create(LOGIN_TEST,PASS_TEST, Role.USUAL);
    }

    @After
    public void after() {
        service.clear();
    }

    @Test
    public void create() throws AbstractException {
        service.create(LOGIN,PASSWORD, Role.USUAL);
        @Nullable final User user = service.findByLogin(LOGIN);
        Assert.assertNotNull(user);
        projectRepository.add(user.getId(),USER_PROJECT);
        Assert.assertNotNull(projectRepository.findById(USER_PROJECT.getId()));
    }

    @Test
    public void findByLogin() throws LoginEmptyException {
        Assert.assertNotNull(service.findByLogin(LOGIN_TEST));
    }


    @Test
    public void removeByLogin() throws AbstractFieldException {
        service.removeByLogin(LOGIN_TEST);
        Assert.assertNull(service.findByLogin(LOGIN_TEST));
    }

    @Test
    public void updateUser() throws AbstractFieldException {
        service.updateUser(userTesting.getId(),NAME,"lastName","middle");
        Assert.assertTrue(Objects.equals(userTesting.getFirstName(), NAME));

    }

    @Test
    public void isLoginExist() {
        Assert.assertTrue(service.isLoginExist(LOGIN_TEST));
    }


}
