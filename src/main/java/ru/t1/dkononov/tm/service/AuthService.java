package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Arrays;


public class AuthService implements ru.t1.dkononov.tm.api.services.IAuthService {

    private final IUserService userService;

    private String userId;

    public AuthService(final IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User registry(final String login, final String password, final String email)
            throws AbstractException {
        return userService.create(login, password, email);
    }

    @Override
    public void login(final String login, final String password)
            throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = userService.findByLogin(login);
        if (user == null) throw new AccessDeniedException();
        final boolean locked = user.isLocked() == null || user.isLocked();
        if (locked) throw new AccessDeniedException();
        final String hash = HashUtil.salt(password);
        if (!hash.equals(user.getPasswordHash())) throw new AccessDeniedException();
        userId = user.getId();
    }

    @Override
    public void logout() {
        userId = null;
    }

    @Override
    public boolean isAuth() {
        return userId != null;
    }

    @Override
    public String getUserId() throws AccessDeniedException {
        if (!isAuth()) throw new AccessDeniedException();
        return userId;
    }

    @Override
    public User getUser() throws AbstractFieldException {
        if (!isAuth()) throw new AccessDeniedException();
        final User user = userService.findById(userId);
        if (user == null) throw new AccessDeniedException();
        return user;
    }

    @Override
    public void checkRoles(final Role[] roles) throws AbstractException {
        if (roles == null) return;
        final User user = getUser();
        final Role role = user.getRole();
        if (role == null) throw new PermissionException();
        final boolean hasRole = Arrays.asList(roles).contains(role);
        if (!hasRole) throw new PermissionException();
    }

}
