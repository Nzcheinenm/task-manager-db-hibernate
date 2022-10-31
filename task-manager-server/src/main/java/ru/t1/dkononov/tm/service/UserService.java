package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class UserService implements IUserService {


    @NotNull
    private final IConnectionService connectionService;

    @NotNull
    private final IPropertyService propertyService;

    public UserService(@NotNull final IPropertyService propertyService, @NotNull final IConnectionService connectionService) {
        this.propertyService = propertyService;
        this.connectionService = connectionService;
    }

    @NotNull
    public ITaskRepository getTaskRepository() {
        return connectionService.getSqlSession().getMapper(ITaskRepository.class);
    }

    @NotNull
    public IProjectRepository getProjectRepository() {
        return connectionService.getSqlSession().getMapper(IProjectRepository.class);
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = new UserDTO();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setRole(Role.USUAL);
            repository.add(user);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = new UserDTO();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            user.setEmail(email);
            repository.add(user);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = new UserDTO();
            user.setLogin(login);
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            if (role != null) user.setRole(role);
            repository.add(user);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO findByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            @Nullable final UserDTO user = repository.findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            return user;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO findByEmail(@Nullable final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            @Nullable final UserDTO user = repository.findByEmail(email);
            if (user == null) return null;
            return user;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO removeOne(@Nullable final UserDTO model) throws UserIdEmptyException {
        if (model == null) return null;
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = removeOne(model);
            if (user == null) return null;
            @Nullable final String userId = user.getId();
            getTaskRepository().clear(userId);
            getProjectRepository().clear(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserDTO removeByLogin(@Nullable final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            user = findByLogin(login);
            removeOne(user);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserDTO removeByEmail(@Nullable final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            user = findByEmail(email);
            removeOne(user);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = repository.findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setPasswordHash(HashUtil.salt(propertyService, password));
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = repository.findById(id);
            if (user == null) throw new UserNotFoundException();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            return repository.findByLogin(login) != null;
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public Boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            return repository.findByEmail(email) != null;
        }
    }

    @Override
    @Nullable
    @SneakyThrows
    public UserDTO lockUserByLogin(@Nullable final String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = findByLogin(login);
            if (user == null) throw new UserNotFoundException();
            user.setLocked(true);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public UserDTO unlockUserByLogin(@Nullable String login) throws LoginEmptyException, UserNotFoundException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final UserDTO user;
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            user = findByLogin(login);
            user.setLocked(false);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public UserDTO findById(@Nullable final String id)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            return repository.findById(id);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<UserDTO> findAll() {
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            return repository.findAll();
        }
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<UserDTO> set(@NotNull Collection<UserDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository repository = sqlSession.getMapper(IUserRepository.class);
            models.forEach(repository::update);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return models;
    }

}
