package ru.t1.dkononov.tm.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Session extends AbstractUserOwnedModel {

    @NotNull
    private Date date = new Date();

    @Nullable
    private Role role = null;

}
