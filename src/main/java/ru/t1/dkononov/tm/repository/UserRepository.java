package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.model.User;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(final String login) {
        return models
                .stream()
                .filter(u -> login.equals(u.getLogin()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByEmail(final String email) {
        return models
                .stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }


    @Override
    public Boolean isLoginExist(final String login) {
        return models
                .stream()
                .anyMatch(u -> login.equals(u.getLogin()));
    }

    @Override
    public Boolean isEmailExist(final String email) {
        return models
                .stream()
                .anyMatch(u -> email.equals(u.getEmail()));
    }

}
