package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.model.User;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(final String login) {
        for (final User user : models) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        for (final User user : models) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }


    @Override
    public Boolean isLoginExist(final String login) {
        for (final User user : models) {
            if (login.equals(user.getLogin())) return true;
        }
        return false;
    }

    @Override
    public Boolean isEmailExist(final String email) {
        for (final User user : models) {
            if (email.equals(user.getEmail())) return true;
        }
        return false;
    }

}
