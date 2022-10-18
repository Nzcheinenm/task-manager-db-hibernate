package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.service.PropertyService;

@Category(IntegrationCategory.class)
public class ProjectEndpointTest {

    @NotNull
    private static final IPropertyService propertyService = new PropertyService();

    @NotNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NotNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    @Nullable
    private static String token;

    @NotNull
    private static String login = "admin";

    @NotNull
    private static String pass = "admin";

}
