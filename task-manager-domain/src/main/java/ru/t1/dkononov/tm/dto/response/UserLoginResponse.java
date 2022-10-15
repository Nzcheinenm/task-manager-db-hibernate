package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor
public final class UserLoginResponse extends AbstractResultResponse {

    public UserLoginResponse(@Nullable final Throwable throwable) {
        super(throwable);
    }

    @Getter
    @NotNull
    public String token;

    public UserLoginResponse(@Nullable final String token) {
        this.token = token;
    }

}
