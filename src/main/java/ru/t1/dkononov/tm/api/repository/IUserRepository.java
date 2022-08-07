package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(String login);

    User findByEmail(String email);

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);

}
