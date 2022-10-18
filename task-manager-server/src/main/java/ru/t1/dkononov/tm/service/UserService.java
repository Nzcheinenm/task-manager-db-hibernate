package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.List;

public final class UserService extends AbstractService<User, IUserRepository> implements IUserService {
    @Nullable
    private final IProjectRepository projectRepository;
    @Nullable
    private final ITaskRepository taskRepository;

    @Nullable
    private final IPropertyService propertyService;

    public UserService(
            @NotNull final IUserRepository repository,
            @Nullable final IProjectRepository projectRepository,
            @Nullable final ITaskRepository taskRepository,
            @Nullable final IPropertyService propertyService) {
        super(repository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.propertyService = propertyService;
    }

    @Nullable
    @Override
    public User create(
            @Nullable final String login,
            @Nullable final String password
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setRole(Role.USUAL);
        return repository.add(user);
    }

    @NotNull
    @Override
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final String email
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NotNull final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @NotNull
    @Override
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final Role role
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NotNull final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Nullable
    @Override
    public User findByLogin(@Nullable final String login) throws LoginEmptyException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = repository.findByLogin(login);
        if (user == null) return null;
        return user;
    }

    @Nullable
    @Override
    public User findByEmail(@Nullable final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @Nullable final User user = repository.findByEmail(email);
        if (user == null) return null;
        return user;
    }

    @Nullable
    @Override
    public User removeOne(@Nullable final User model) throws UserIdEmptyException {
        if (model == null) return null;
        @Nullable final User user = super.remove(model);
        if (user == null) return null;
        @Nullable final String userId = user.getId();
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
        return user;
    }

    @NotNull
    @Override
    public User removeByLogin(@Nullable final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = findByLogin(login);
        return remove(user);
    }

    @NotNull
    @Override
    public User removeByEmail(@Nullable final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @Nullable final User user = findByEmail(email);
        return remove(user);
    }

    @NotNull
    @Override
    public User setPassword(
            @Nullable final String id,
            @Nullable final String password
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        return user;
    }

    @NotNull
    @Override
    public User updateUser(
            @Nullable final String id,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @Nullable final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

    @Override
    public Boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        return repository.isLoginExist(login);
    }

    @Override
    public Boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        return repository.isEmailExist(email);
    }

    @Override
    @Nullable
    public User lockUserByLogin(@Nullable final String login) throws LoginEmptyException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = findByLogin(login);
        user.setLocked(true);
        return user;
    }

    @Override
    @Nullable
    public User unlockUserByLogin(@Nullable String login) throws LoginEmptyException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = findByLogin(login);
        user.setLocked(false);
        return user;
    }

    @Override
    public void removeAll(@Nullable List<User> modelsRemove) {
    }

}
