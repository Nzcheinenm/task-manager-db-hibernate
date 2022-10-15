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
public abstract class AbstractUserRequest extends AbstractRequest {

    @Nullable
    private String token;

    public AbstractUserRequest(@NotNull String token) {
        this.token = token;
    }


}
