package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.EmailEmptyException;
import ru.t1.dkononov.tm.exception.field.LoginEmptyException;
import ru.t1.dkononov.tm.model.User;

public interface IUserService extends IService<User> {
    User create(String login, String password) throws AbstractException;

    User create(String login, String password, String email) throws AbstractException;

    User create(String login, String password, Role role) throws AbstractException;

    User findByLogin(String login) throws LoginEmptyException;

    User findByEmail(String email) throws EmailEmptyException;

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
