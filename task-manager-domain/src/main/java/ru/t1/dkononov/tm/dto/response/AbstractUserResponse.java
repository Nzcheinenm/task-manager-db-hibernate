package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUserResponse extends AbstractResponse {

    @NotNull
    private User user;

    public AbstractUserResponse(@NotNull User user) {
        this.user = user;
    }

}
