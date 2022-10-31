package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.model.UserDTO;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUserResponse extends AbstractResponse {

    @NotNull
    private UserDTO user;

    public AbstractUserResponse(@NotNull UserDTO user) {
        this.user = user;
    }

}
