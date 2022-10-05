package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public final class UserUnlockResponse extends AbstractUserResponse {
    public UserUnlockResponse(@NotNull final User user) {
        super(user);
    }
}
