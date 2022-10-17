package ru.t1.dkononov.tm.constant;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.component.ISaltProvider;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.service.PropertyService;
import ru.t1.dkononov.tm.util.CryptUtil;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Date;

@UtilityClass
public class TestData {

    @NotNull
    public final static Project USER_PROJECT = new Project();

    @NotNull
    public final static Project USER_PROJECT2 = new Project();

    @NotNull
    public final static Project ADMIN_PROJECT = new Project();

    @NotNull
    public final static Task USER_TASK = new Task();

    @NotNull
    public final static Task USER_TASK2 = new Task();

    @NotNull
    public final static Task ADMIN_TASK = new Task();

    @NotNull
    public final static User USER1 = new User();

    @NotNull
    public final static User USER2 = new User();

    @NotNull
    public final static Project NULL_PROJECT = null;

    @NotNull
    public final static Task NULL_TASK = null;

    @NotNull
    public final static Session SESSION = new Session();

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
