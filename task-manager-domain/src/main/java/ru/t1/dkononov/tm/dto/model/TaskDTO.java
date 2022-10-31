package ru.t1.dkononov.tm.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tm_task")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TaskDTO extends AbstractUserOwnedModelDTO implements IWBS {

    private final static long serialVersionUID = 1;

    @Column
    @NotNull
    private String name = "";

    @Column
    @NotNull
    private String description = "";

    @Column
    @NotNull
    private Status status = Status.NOT_STARTED;

    @Column(name = "project_id")
    @Nullable
    private String projectId;

    @Column
    @NotNull
    private Date created = new Date();

    public TaskDTO(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
