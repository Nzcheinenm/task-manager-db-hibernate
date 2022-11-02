package ru.t1.dkononov.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tm_session")
@NoArgsConstructor
public final class Session extends AbstractUserOwnedModel {

    @NotNull
    private Date date = new Date();

    @Nullable
    @Enumerated(EnumType.STRING)
    private Role role = null;

}
