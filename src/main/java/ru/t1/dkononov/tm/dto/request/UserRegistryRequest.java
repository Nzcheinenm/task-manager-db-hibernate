package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class UserRegistryRequest extends AbstractUserRequest {

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String email;

}
