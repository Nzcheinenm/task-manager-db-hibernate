package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class UserChangePasswordRequest extends AbstractUserRequest {

    @Nullable
    private String password;

    public UserChangePasswordRequest(@NotNull final String token) {
        super(token);
    }

}
