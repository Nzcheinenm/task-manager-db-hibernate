package ru.t1.dkononov.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tm_user")
@NoArgsConstructor
public final class User extends AbstractModel {

    private final static long serialVersionUID = 1;

    @Column
    @Nullable
    private String login;

    @Nullable
    @Column(name = "password")
    private String passwordHash;

    @Column
    @Nullable
    private String email;

    @Nullable
    @Column(name = "first_name")
    private String firstName;

    @Nullable
    @Column(name = "middle_name")
    private String middleName;

    @Nullable
    @Column(name = "last_name")
    private String lastName;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USUAL;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    @Column
    @NotNull
    private Boolean locked = false;

    public void setLocked(@NotNull Boolean locked) {
        this.locked = locked;
    }

}
