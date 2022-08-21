package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    @Nullable
    User findByLogin(@NotNull String login);

    @Nullable
    User findByEmail(@NotNull String email);

    Boolean isLoginExist(@NotNull String login);

    Boolean isEmailExist(@NotNull String email);

}
