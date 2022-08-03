package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;

import java.util.List;

public interface IUserService {
    User create(String login, String password) throws AbstractFieldException;

    User create(String login, String password, String email) throws AbstractFieldException;

    User create(String login, String password, Role role) throws AbstractFieldException;

    User add(User user) throws UserNotFoundException;

    List<User> findAll();

    User findById(String id) throws IdEmptyException;

    User findByLogin(String login) throws LoginEmptyException;

    User findByEmail(String email) throws EmailEmptyException;

    User remove(User user) throws UserNotFoundException;

    User removeById(String id) throws AbstractFieldException;

    User removeByLogin(String login) throws AbstractFieldException;

    User removeByEmail(String email) throws AbstractFieldException;

    User setPassword(String id, String password) throws AbstractFieldException;

    User updateUser(
            String id,
            String firstName,
            String lastName,
            String middleName
    ) throws AbstractFieldException;

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);
}
