package ru.t1.dkononov.tm.service;

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
import ru.t1.dkononov.tm.exception.field.UserNotFoundException;
import ru.t1.dkononov.tm.marker.UnitCategory;
import ru.t1.dkononov.tm.service.model.UserService;

import java.util.Objects;

import static ru.t1.dkononov.tm.constant.TestData.LOGIN;
import static ru.t1.dkononov.tm.constant.TestData.PASSWORD;


@Category(UnitCategory.class)
public class UserServiceTest {


    @NotNull
    private final IConnectionService connectionService = new ConnectionService(new PropertyService());

    @NotNull
    private final PropertyService propertyService = new PropertyService();

    @NotNull
    private final UserService service = new UserService(connectionService, propertyService);

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
        userTesting = service.create(LOGIN_TEST, PASS_TEST, Role.USUAL);
    }

    @After
    public void after() {
        service.clear();
    }

    @Test
    public void create() throws AbstractException {
        service.create(LOGIN, PASSWORD, Role.USUAL);
        @Nullable final UserDTO user = service.findByLogin(LOGIN);
        Assert.assertNotNull(user);
    }

    @Test
    public void findByLogin() throws LoginEmptyException, UserNotFoundException {
        Assert.assertNotNull(service.findByLogin(LOGIN_TEST));
    }


    @Test
    public void removeByLogin() throws AbstractFieldException {
        service.removeByLogin(LOGIN_TEST);
        Assert.assertThrows(Exception.class, () -> service.findByLogin(LOGIN_TEST));
    }

    @Test
    public void updateUser() throws AbstractFieldException {
        service.updateUser(userTesting.getId(), NAME, "lastName", "middle");
        Assert.assertTrue(Objects.equals(userTesting.getFirstName(), NAME));

    }

    @Test
    public void isLoginExist() {
        Assert.assertTrue(service.isLoginExist(LOGIN_TEST));
    }


}
