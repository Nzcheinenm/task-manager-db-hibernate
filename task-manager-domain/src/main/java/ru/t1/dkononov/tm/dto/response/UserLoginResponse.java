package ru.t1.dkononov.tm.dto.response;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor
public final class UserLoginResponse extends AbstractResultResponse {

    public UserLoginResponse(@Nullable final Throwable throwable) {
        super(throwable);
    }

}
