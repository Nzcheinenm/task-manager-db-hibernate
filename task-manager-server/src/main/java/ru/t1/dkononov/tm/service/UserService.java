package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.*;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;
import ru.t1.dkononov.tm.util.HashUtil;

import java.sql.Connection;
import java.util.List;

public final class UserService extends AbstractService<User, IUserRepository> implements IUserService {


    @NotNull final IPropertyService propertyService;

    public UserService(
            @NotNull final IConnectionService connectionService,
            @NotNull final IPropertyService propertyService
    ) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    @Override
    @NotNull
    protected IUserRepository getRepository(@NotNull Connection connection) {
        return new UserRepository(connection);
    }


    @NotNull
    public ITaskRepository getTaskRepository(@NotNull Connection connection) {
        return new TaskRepository(connection);
    }

    @NotNull
    public IProjectRepository getProjectRepository(@NotNull Connection connection) {
        return new ProjectRepository(connection);
    }

    @Nullable
    @Override
    @SneakyThrows
    public User create(
            @Nullable final String login,
            @Nullable final String password
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setRole(Role.USUAL);
            repository.add(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final String email
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setEmail(email);
            repository.add(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final Role role
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            if (role != null) user.setRole(role);
            repository.add(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserRepository repository = getRepository(connection);
            @Nullable final User user = repository.findByLogin(login);
            if (user == null) throw new  UserNotFoundException();
            return user;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByEmail(@Nullable final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserRepository repository = getRepository(connection);
            @Nullable final User user = repository.findByEmail(email);
            if (user == null) return null;
            return user;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public User removeOne(@Nullable final User model) throws UserIdEmptyException {
        if (model == null) return null;
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = remove(model);
            if (user == null) return null;
            @Nullable final String userId = user.getId();
            getTaskRepository(connection).removeAll(userId);
            getProjectRepository(connection).removeAll(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User removeByLogin(@Nullable final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            user = findByLogin(login);
            remove(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User removeByEmail(@Nullable final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            user = findByEmail(email);
            remove(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User setPassword(
            @Nullable final String id,
            @Nullable final String password
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User updateUser(
            @Nullable final String id,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserRepository repository = getRepository(connection);
            return repository.isLoginExist(login);
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final IUserRepository repository = getRepository(connection);
            return repository.isEmailExist(email);
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public User lockUserByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            user.setLocked(true);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public User unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final User user;
        try {
            @NotNull final IUserRepository repository = getRepository(connection);
            user = findByLogin(login);
            user.setLocked(false);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @Override
    public void removeAll(@Nullable List<User> modelsRemove) {
    }

}
