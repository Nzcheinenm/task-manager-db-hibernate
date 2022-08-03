package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.User;

import java.util.List;

public interface IUserRepository {
    User add(User user);

    List<User> findAll();

    User findById(String id);

    User findByLogin(String login);

    User findByEmail(String email);

    User remove(User user);

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);
}
