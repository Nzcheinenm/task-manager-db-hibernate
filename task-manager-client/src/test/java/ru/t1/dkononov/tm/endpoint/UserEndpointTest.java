package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestClientData.USER2_TEST_LOGIN;
import static ru.t1.dkononov.tm.constant.TestClientData.USER2_TEST_PASS;

@Category(IntegrationCategory.class)
public class UserEndpointTest {

    @NotNull
    private static final IPropertyService propertyService = new PropertyService();

    @NotNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NotNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    @Nullable
    private static String token;

    @NotNull
    private final static String login = "admin";

    @NotNull
    private final static String pass = "admin";

    @BeforeClass
    public static void setUp() throws Exception {
        @NotNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setPassword(pass);
        token = authEndpoint.login(loginRequest).getToken();
    }

    @Test
    public void updateUser() throws AbstractFieldException {
        @NotNull final UserUpdateProfileRequest request = new UserUpdateProfileRequest(token);
        @NotNull final String firstName = "Userty";
        request.setFirstName(firstName);
        request.setMiddleName("Middle");
        request.setLastName("Last");
        @NotNull final UserDTO user = userEndpoint.updateUserProfile(request).getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void isLoginExist() throws Exception {
        @NotNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setPassword(pass);
        token = authEndpoint.login(loginRequest).getToken();
        Assert.assertNotNull(token);
        @NotNull final UserRegistryRequest request = new UserRegistryRequest(token);
        request.setLogin(USER2_TEST_LOGIN);
        request.setPassword(USER2_TEST_PASS);
        Assert.assertThrows(Exception.class, () -> userEndpoint.registryUser(request));
    }

    @Test
    public void lockUserByLogin() throws Exception {
        @NotNull final UserLockRequest request = new UserLockRequest(token);
        request.setLogin(USER2_TEST_LOGIN);
        Assert.assertNotNull(userEndpoint.lockUser(request));
    }

    @Test
    public void unlockUserByLogin() throws Exception {
        @NotNull final UserUnlockRequest request = new UserUnlockRequest(token);
        request.setLogin(USER2_TEST_LOGIN);
        Assert.assertNotNull(userEndpoint.unlockUser(request));
    }

}
