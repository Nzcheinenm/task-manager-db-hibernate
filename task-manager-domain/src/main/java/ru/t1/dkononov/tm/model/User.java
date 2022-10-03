package ru.t1.dkononov.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class User extends AbstractModel {

    private final static long serialVersionUID = 1;

    @Nullable
    private String login;

    @Nullable
    private String passwordHash;

    @Nullable
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String middleName;

    @Nullable
    private String lastName;

    @NotNull
    private Role role = Role.USUAL;

    @NotNull
    private Boolean locked = false;

    public Boolean isLocked() {
        return locked;
    }

}
