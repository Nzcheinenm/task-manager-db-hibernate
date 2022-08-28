package ru.t1.dkononov.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Project extends AbstractUserOwnedModel implements IWBS {

    @NotNull
    private String name = "";

    @NotNull
    private Status status = Status.NOT_STARTED;

    @NotNull
    private String description = "";

    @NotNull
    private Date created = new Date();

    public Project(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
