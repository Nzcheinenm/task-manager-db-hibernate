package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public User add(final User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(final String id) {
        for (final User user : users) {
            if (id.equals(user.getId())) return user;
        }
        return null;
    }

    @Override
    public User findByLogin(final String login) {
        for (final User user : users) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        for (final User user : users) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    @Override
    public User remove(final User user) {
        users.remove(user);
        return user;
    }

    @Override
    public Boolean isLoginExist(final String login) {
        for (final User user : users) {
            if (login.equals(user.getLogin())) return true;
        }
        return false;
    }

    @Override
    public Boolean isEmailExist(final String email) {
        for (final User user : users) {
            if (email.equals(user.getEmail())) return true;
        }
        return false;
    }

}
