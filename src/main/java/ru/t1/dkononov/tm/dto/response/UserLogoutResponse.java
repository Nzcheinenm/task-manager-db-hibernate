package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public final class UserLogoutResponse extends AbstractUserResponse {

    public UserLogoutResponse(@Nullable final User user) {
        super(user);
    }

}
