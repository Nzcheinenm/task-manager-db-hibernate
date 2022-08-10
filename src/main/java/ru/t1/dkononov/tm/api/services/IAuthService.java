package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.User;

public interface IAuthService {
    User registry(String login, String password, String email)
            throws AbstractException;

    void login(String login, String password)
            throws AbstractFieldException;

    void logout();

    boolean isAuth();

    String getUserId() throws AccessDeniedException;

    User getUser() throws AbstractFieldException;

    void checkRoles(Role[] roles) throws AbstractException;
}
