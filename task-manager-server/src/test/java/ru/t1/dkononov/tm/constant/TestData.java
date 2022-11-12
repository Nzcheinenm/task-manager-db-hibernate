package ru.t1.dkononov.tm.constant;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.component.ISaltProvider;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.service.PropertyService;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Date;

@UtilityClass
public class TestData {

    @NotNull
    public final static ProjectDTO USER_PROJECT = new ProjectDTO();

    @NotNull
    public final static ProjectDTO USER_PROJECT2 = new ProjectDTO();

    @NotNull
    public final static ProjectDTO ADMIN_PROJECT = new ProjectDTO();

    @NotNull
    public final static TaskDTO USER_TASK = new TaskDTO();

    @NotNull
    public final static TaskDTO USER_TASK2 = new TaskDTO();

    @NotNull
    public final static TaskDTO ADMIN_TASK = new TaskDTO();

    @NotNull
    public final static UserDTO USER1 = new UserDTO();

    @NotNull
    public final static UserDTO USER2 = new UserDTO();

    @NotNull
    public final static ProjectDTO NULL_PROJECT = null;

    @NotNull
    public final static TaskDTO NULL_TASK = null;

    @NotNull
    public final static SessionDTO SESSION = new SessionDTO();

    @NotNull
    public final static SessionDTO SESSION_NULL = null;

    @NotNull
    public static final String LOGIN = "login";

    @NotNull
    public static final String PASSWORD = "pass";

    static {
        @Nullable final ISaltProvider propertyService = new PropertyService();

        USER1.setLogin("Test1");
        USER1.setFirstName("Testing");
        USER1.setLastName("First");
        USER1.setRole(Role.USUAL);
        USER1.setPasswordHash(HashUtil.salt(propertyService, "password"));

        USER2.setLogin("Test2");
        USER2.setFirstName("Testing");
        USER2.setLastName("Second");
        USER2.setRole(Role.ADMIN);
        USER2.setPasswordHash(HashUtil.salt(propertyService, "password"));

        USER_PROJECT.setUserId(USER1.getId());
        USER_PROJECT.setName("First");
        USER_PROJECT.setStatus(Status.IN_PROGRESS);
        USER_PROJECT.setDescription("project");

        USER_PROJECT2.setUserId(USER1.getId());
        USER_PROJECT2.setName("Second User");
        USER_PROJECT2.setStatus(Status.IN_PROGRESS);
        USER_PROJECT2.setDescription("project");

        ADMIN_PROJECT.setUserId(USER2.getId());
        ADMIN_PROJECT.setName("Second");
        ADMIN_PROJECT.setStatus(Status.COMPLETED);
        ADMIN_PROJECT.setDescription("project");

        USER_TASK.setUserId(USER1.getId());
        USER_TASK.setName("First");
        USER_TASK.setStatus(Status.IN_PROGRESS);
        USER_TASK.setDescription("task");

        ADMIN_TASK.setUserId(USER2.getId());
        ADMIN_TASK.setName("Second");
        ADMIN_TASK.setStatus(Status.COMPLETED);
        ADMIN_TASK.setDescription("task");

        SESSION.setDate(new Date());
        SESSION.setRole(Role.USUAL);
        SESSION.setUserId(USER1.getId());
    }

}
