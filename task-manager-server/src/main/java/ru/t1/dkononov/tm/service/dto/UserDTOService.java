package ru.t1.dkononov.tm.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.dto.IDTORepository;
import ru.t1.dkononov.tm.api.repository.dto.IProjectDTORepository;
import ru.t1.dkononov.tm.api.repository.dto.ITaskDTORepository;
import ru.t1.dkononov.tm.api.repository.dto.IUserDTORepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.dto.IUserDTOService;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.repository.dto.ProjectDTORepository;
import ru.t1.dkononov.tm.repository.dto.TaskDTORepository;
import ru.t1.dkononov.tm.repository.dto.UserDTORepository;
import ru.t1.dkononov.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class UserDTOService extends AbstractDTOService<UserDTO, UserDTORepository> implements IUserDTOService {

    @NotNull
    private final IPropertyService propertyService;

    public UserDTOService(@NotNull final IPropertyService propertyService, @NotNull final IConnectionService connectionService) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    @Override
    @NotNull
    public ITaskDTORepository getTaskRepository() {
        return new TaskDTORepository(getEntityManager());
    }

    @Override
    @NotNull
    public IProjectDTORepository getProjectRepository() {
        return new ProjectDTORepository(getEntityManager());
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO create(
            @Nullable final String login,
            @Nullable final String password
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
            user = new UserDTO();
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
    public UserDTO create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final String email
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
            user = new UserDTO();
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
    public UserDTO create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final Role role
    ) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
            user = new UserDTO();
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
    public UserDTO findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserDTORepository repository = (IUserDTORepository) getRepository(entityManager);
            @Nullable final UserDTO user = repository.findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO findByEmail(@Nullable final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IUserDTORepository repository = (IUserDTORepository) getRepository(entityManager);
            @Nullable final UserDTO user = repository.findByEmail(email);
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO removeOne(@Nullable final UserDTO model) throws UserIdEmptyException {
        if (model == null) return null;
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
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
    public UserDTO removeByLogin(@Nullable final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
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
    public UserDTO removeByEmail(@Nullable final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
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
    public UserDTO setPassword(
            @Nullable final String id,
            @Nullable final String password
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
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
    public UserDTO updateUser(
            @Nullable final String id,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
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
            @NotNull final IUserDTORepository repository = (IUserDTORepository) getRepository(entityManager);
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
            @NotNull final IUserDTORepository repository = (IUserDTORepository) getRepository(entityManager);
            return repository.findByEmail(email) != null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public UserDTO lockUserByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserDTORepository repository = (IUserDTORepository) getRepository(entityManager);
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
    public UserDTO unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        @Nullable final UserDTO user;
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
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
    public UserDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
            return repository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Nullable
    public List<UserDTO> findAll(@Nullable Sort sort) {
        return findAll();
    }

    @Override
    protected @NotNull IDTORepository<UserDTO> getRepository(@NotNull EntityManager entityManager) {
        return new UserDTORepository(entityManager);
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<UserDTO> findAll() {
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
            return repository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<UserDTO> set(@NotNull Collection<UserDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IDTORepository<UserDTO> repository = getRepository(entityManager);
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
