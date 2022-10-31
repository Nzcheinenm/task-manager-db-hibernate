package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.TaskListResponse;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.service.PropertyService;

import static ru.t1.dkononov.tm.constant.TestClientData.USER2_TEST_LOGIN;
import static ru.t1.dkononov.tm.constant.TestClientData.USER2_TEST_PASS;

@Category(IntegrationCategory.class)
public class TaskEndpointTest {

    @NotNull
    private static final IPropertyService propertyService = new PropertyService();

    @NotNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);


    @NotNull
    private static final ITaskEndpoint taskEndpoint = ITaskEndpoint.newInstance(propertyService);

    @Nullable
    private static String token;


    @Nullable
    private TaskDTO task;

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
        @NotNull final TaskCreateRequest request = new TaskCreateRequest(getUserToken());
        request.setName(name);
        request.setDescription(description);
        task = taskEndpoint.createTask(request).getTask();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        @NotNull final TaskClearRequest request = new TaskClearRequest(token);
        taskEndpoint.clearTask(request);
    }

    @Test
    public void clearTask() throws Exception {
        @NotNull final TaskClearRequest request = new TaskClearRequest(getUserToken());
        Assert.assertNull(taskEndpoint.clearTask(request).getTask());
    }

    @Test
    public void createTask() throws Exception {
        @NotNull final String name = "Task";
        @NotNull final String description = "Description";
        @NotNull final TaskCreateRequest request = new TaskCreateRequest(getUserToken());
        request.setName(name);
        request.setDescription(description);
        Assert.assertNotNull(taskEndpoint.createTask(request).getTask());
    }

    @Test
    public void getTaskById() throws Exception {
        @NotNull final TaskGetByIdRequest request = new TaskGetByIdRequest(getUserToken());
        request.setId(task.getId());
        Assert.assertNotNull(taskEndpoint.getTaskById(request).getTask());
    }

    @Test
    public void getTaskByIndex() throws Exception {
        @NotNull final TaskGetByIndexRequest request = new TaskGetByIndexRequest(getUserToken());
        request.setIndex(0);
        Assert.assertNotNull(taskEndpoint.getTaskByIndex(request).getTask());
    }

    @Test
    public void listTask() throws Exception {
        @Nullable final Sort sort = null;
        @NotNull final TaskListRequest request = new TaskListRequest(getUserToken());
        request.setSort(sort);
        @NotNull final TaskListResponse response = taskEndpoint.listTask(request);
        Assert.assertNotNull(response.getTasks());
    }

    @Test
    public void removeTaskById() throws Exception {
        @NotNull final TaskRemoveByIdRequest request = new TaskRemoveByIdRequest(getUserToken());
        request.setId(task.getId());
        Assert.assertNull(taskEndpoint.removeTaskById(request).getTask());
    }

    @Test
    public void startTaskById() throws Exception {
        @NotNull final TaskStartByIdRequest request = new TaskStartByIdRequest(getUserToken());
        request.setId(task.getId());
        Assert.assertNotNull(taskEndpoint.startTaskById(request).getTask());
    }

    @Test
    public void completeTaskById() throws Exception {
        @NotNull final TaskCompleteByIdRequest request = new TaskCompleteByIdRequest(getUserToken());
        request.setId(task.getId());
        Assert.assertNotNull(taskEndpoint.completeTaskById(request).getTask());
    }


    @Test
    public void updateTaskById() throws Exception {
        @NotNull final TaskUpdateByIdRequest request = new TaskUpdateByIdRequest(getUserToken());
        request.setId(task.getId());
        request.setName("Second Task");
        request.setDescription("Desc");
        Assert.assertNotNull(taskEndpoint.updateTaskById(request).getTask());
    }

}
