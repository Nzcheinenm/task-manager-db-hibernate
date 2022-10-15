package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class UserLockRequest extends AbstractUserRequest {

    @Nullable
    private String login;

    public UserLockRequest(@NotNull final String token) {
        super(token);
    }
    
}
