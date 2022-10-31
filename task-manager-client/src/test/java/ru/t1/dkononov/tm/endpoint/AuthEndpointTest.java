package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestClientData.USER_TEST_LOGIN;
import static ru.t1.dkononov.tm.constant.TestClientData.USER_TEST_PASS;

@Category(IntegrationCategory.class)
public final class AuthEndpointTest {

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
        @NotNull final UserRegistryRequest request = new UserRegistryRequest(token);
        request.setLogin(USER_TEST_LOGIN);
        request.setPassword(USER_TEST_PASS);
        request.setEmail("test@testing.com");
        userEndpoint.registryUser(request);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        @NotNull final UserRemoveRequest request = new UserRemoveRequest(token);
        request.setLogin(USER_TEST_LOGIN);
        userEndpoint.removeUser(request);
    }

    private String getUserToken() throws Exception {
        @NotNull final UserLoginRequest request = new UserLoginRequest();
        request.setLogin(USER_TEST_LOGIN);
        request.setPassword(USER_TEST_PASS);
        return authEndpoint.login(request).getToken();
    }

    @Test
    public void loginUser() throws Exception {
        @Nullable final String token = getUserToken();
        Assert.assertNotNull(token);
        @NotNull final UserProfileRequest request = new UserProfileRequest(token);
        Assert.assertNotNull(authEndpoint.profile(request).getUser());
    }

    @Test
    public void logoutUser() throws Exception {
        @Nullable final String token = getUserToken();
        @NotNull final UserLogoutRequest request = new UserLogoutRequest(token);
        authEndpoint.logout(request);
        @NotNull final UserProfileRequest userProfileRequest = new UserProfileRequest(token);
        Assert.assertNotNull(authEndpoint.profile(userProfileRequest));
    }

}
