package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class UserUpdateProfileRequest extends AbstractUserRequest {

    @Nullable
    private String firstName;

    @Nullable
    private String middleName;

    @Nullable
    private String lastName;

    public UserUpdateProfileRequest(@NotNull final String token) {
        super(token);
    }

}
