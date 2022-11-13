package ru.t1.dkononov.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.model.*;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.model.ProjectRepository;
import ru.t1.dkononov.tm.repository.model.TaskRepository;
import ru.t1.dkononov.tm.repository.model.UserRepository;
import ru.t1.dkononov.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class UserService extends AbstractService<User, UserRepository> implements IUserService {

    @NotNull
    private final IPropertyService propertyService;

    public UserService(@NotNull final IPropertyService propertyService, @NotNull final IConnectionService connectionService) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    @NotNull
    public ITaskRepository getTaskRepository() {
        return new TaskRepository(getEntityManager());
    }

    @NotNull
    public IProjectRepository getProjectRepository() {
        return new ProjectRepository(getEntityManager());
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setRole(Role.USUAL);
            repository.add(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setEmail(email);
            repository.add(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = new User();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            if (role != null) user.setRole(role);
            repository.add(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            @Nullable final User user = repository.findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByEmail(@Nullable final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            @Nullable final User user = repository.findByEmail(email);
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public User removeOne(@Nullable final User model) throws UserIdEmptyException {
        if (model == null) return null;
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = remove(model);
            if (user == null) return null;
            @Nullable final String userId = user.getId();
            getTaskRepository().clear(userId);
            getProjectRepository().clear(userId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User removeByLogin(@Nullable final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            user = findByLogin(login);
            removeOne(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User removeByEmail(@Nullable final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            user = findByEmail(email);
            removeOne(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = repository.findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = repository.findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            return repository.findByLogin(login) != null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            return repository.findByEmail(email) != null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public User lockUserByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            user = findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            user.setLocked(true);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public User unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final User user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            user = findByLogin(login);
            user.setLocked(false);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            return repository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Nullable
    public List<User> findAll(@Nullable Sort sort) {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserRepository repository = (IUserRepository) getRepository(entityManager);
            return repository.findAll(sort);
        } finally {
            entityManager.close();
        }
    }

    @Override
    protected @NotNull IRepository<User> getRepository(@NotNull EntityManager entityManager) {
        return new UserRepository(entityManager);
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<User> findAll() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<User> set(@NotNull Collection<User> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IRepository<User> repository = getRepository(entityManager);
            models.forEach(repository::update);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return models;
    }

}
