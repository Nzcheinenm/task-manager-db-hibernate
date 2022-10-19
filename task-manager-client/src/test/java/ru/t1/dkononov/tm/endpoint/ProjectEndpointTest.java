package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IProjectEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.ProjectListResponse;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestClientData.*;

@Category(IntegrationCategory.class)
public class ProjectEndpointTest {

    @NotNull
    private static final IPropertyService propertyService = new PropertyService();

    @NotNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);


    @NotNull
    private static final IProjectEndpoint projectEndpoint = IProjectEndpoint.newInstance(propertyService);

    @Nullable
    private static String token;

    @Nullable
    private Project project;

    @BeforeClass
    public static void setUp() throws Exception {
        @NotNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin(USER2_TEST_LOGIN);
        loginRequest.setPassword(USER2_TEST_PASS);
        token = authEndpoint.login(loginRequest).getToken();
    }

    private String getUserToken() throws Exception {
        @NotNull final UserLoginRequest request = new UserLoginRequest();
        request.setLogin(USER2_TEST_LOGIN);
        request.setPassword(USER2_TEST_PASS);
        return authEndpoint.login(request).getToken();
    }

    @Before
    public void before() throws Exception {
        @NotNull final String name = "Name";
        @NotNull final String description = "Description";
        @NotNull final ProjectCreateRequest request = new ProjectCreateRequest(getUserToken());
        request.setName(name);
        request.setDescription(description);
        project = projectEndpoint.createProject(request).getProject();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        @NotNull final ProjectClearRequest request = new ProjectClearRequest(token);
        projectEndpoint.clearProject(request);
    }

    @Test
    public void changeStatusById() throws Exception {
        @Nullable final Status status = Status.IN_PROGRESS;
        @NotNull final ProjectChangeStatusByIdRequest request = new ProjectChangeStatusByIdRequest(getUserToken());
        request.setId(project.getId());
        request.setStatusValue(status.name());
        Assert.assertNotNull(projectEndpoint.changeStatusById(request).getProject());
    }

    @Test
    public void  changeStatusByIndex() throws Exception {
        @Nullable final Status status = Status.IN_PROGRESS;
        @NotNull final ProjectChangeStatusByIndexRequest request = new ProjectChangeStatusByIndexRequest(getUserToken());
        request.setIndex(0);
        request.setStatusValue(status.name());
        Assert.assertNotNull(projectEndpoint.changeStatusByIndex(request).getProject());
    }

    @Test
    public void clearProject() throws Exception {
        @NotNull final ProjectClearRequest request = new ProjectClearRequest(getUserToken());
        Assert.assertNull(projectEndpoint.clearProject(request).getProject());
    }

    @Test
    public void createProject() throws Exception {
        @NotNull final String name = "Project";
        @NotNull final String description = "Description";
        @NotNull final ProjectCreateRequest request = new ProjectCreateRequest(getUserToken());
        request.setName(name);
        request.setDescription(description);
        Assert.assertNotNull(projectEndpoint.createProject(request).getProject());
    }

    @Test
    public void getProjectById() throws Exception {
        @NotNull final ProjectGetByIdRequest request = new ProjectGetByIdRequest(getUserToken());
        request.setId(project.getId());
        Assert.assertNotNull(projectEndpoint.getProjectById(request).getProject());
    }

    @Test
    public void getProjectByIndex() throws Exception {
        @NotNull final ProjectGetByIndexRequest request = new ProjectGetByIndexRequest(getUserToken());
        request.setIndex(0);
        Assert.assertNotNull(projectEndpoint.getProjectByIndex(request).getProject());
    }

    @Test
    public void listProject() throws Exception {
        @Nullable final Sort sort = null;
        @NotNull final ProjectListRequest request = new ProjectListRequest(getUserToken());
        request.setSort(sort);
        int index = 0;
        @NotNull final ProjectListResponse response = projectEndpoint.listProject(request);
        Assert.assertNotNull(response.getProjects());
    }

    @Test
    public void removeProjectById() throws Exception {
        @NotNull final ProjectRemoveByIdRequest request = new ProjectRemoveByIdRequest(getUserToken());
        request.setId(project.getId());
        Assert.assertNull(projectEndpoint.removeProjectById(request).getProject());
    }

    @Test
    public void startProjectById() throws Exception {
        @NotNull final ProjectStartByIdRequest request = new ProjectStartByIdRequest(getUserToken());
        request.setId(project.getId());
        Assert.assertNotNull(projectEndpoint.startProjectById(request).getProject());
    }

    @Test
    public void completeProjectById() throws Exception {
        @NotNull final ProjectCompleteByIdRequest request = new ProjectCompleteByIdRequest(getUserToken());
        request.setId(project.getId());
        Assert.assertNotNull(projectEndpoint.completeProjectById(request).getProject());
    }


    @Test
    public void updateProjectById() throws Exception {
        @NotNull final ProjectUpdateByIdRequest request = new ProjectUpdateByIdRequest(getUserToken());
        request.setId(project.getId());
        request.setName("Second Project");
        request.setDescription("Desc");
        Assert.assertNotNull(projectEndpoint.updateProjectById(request).getProject());
    }


}
