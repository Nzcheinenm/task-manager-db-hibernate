package ru.t1.dkononov.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Task extends AbstractUserOwnedModel implements IWBS {

    @NotNull
    private String name = "";

    @NotNull
    private String description = "";

    @NotNull
    private Status status = Status.NOT_STARTED;

    @Nullable
    private String projectId;

    @NotNull
    private Date created = new Date();

    public Task(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
