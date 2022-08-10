package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.List;

public final class UserService extends AbstractService<User, IUserRepository> implements IUserService {

    public UserService(IUserRepository repository) {
        super(repository);
    }

    @Override
    public User create(final String login, final String password) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return repository.add(user);
    }

    @Override
    public User create(final String login, final String password, final String email) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(final String login, final String password, final Role role) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Override
    public User findByLogin(final String login) throws LoginEmptyException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = repository.findByLogin(login);
        if (user == null) return null;
        return user;
    }

    @Override
    public User findByEmail(final String email) throws EmailEmptyException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = repository.findByEmail(email);
        if (user == null) return null;
        return user;
    }


    @Override
    public User removeByLogin(final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        return remove(user);
    }

    @Override
    public User removeByEmail(final String email) throws AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        return remove(user);
    }

    @Override
    public User setPassword(final String id, final String password) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setPasswordHash(HashUtil.salt(password));
        return user;
    }

    @Override
    public User updateUser(
            final String id,
            final String firstName,
            final String lastName,
            final String middleName
    ) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

    @Override
    public Boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        return repository.isLoginExist(login);
    }

    @Override
    public Boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        return repository.isEmailExist(email);
    }

    @Override
    public void removeAll(List<User> modelsRemove) {
        return;
    }

}
