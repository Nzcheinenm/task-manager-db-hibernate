package ru.t1.dkononov.tm.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tm_user")
@NoArgsConstructor
public final class UserDTO extends AbstractModelDTO {

    private final static long serialVersionUID = 1;

    @Column
    @Nullable
    private String login;

    @Column
    @Nullable
    private String passwordHash;

    @Column
    @Nullable
    private String email;

    @Column
    @Nullable
    private String firstName;

    @Column
    @Nullable
    private String middleName;

    @Column
    @Nullable
    private String lastName;

    @Column
    @NotNull
    private Role role = Role.USUAL;

    @Column
    @NotNull
    private Boolean locked = false;

    public void setLocked(@NotNull Boolean locked) {
        this.locked = locked;
    }

}
