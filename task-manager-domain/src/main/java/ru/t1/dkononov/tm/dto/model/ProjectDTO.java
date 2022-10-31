package ru.t1.dkononov.tm.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name =  "tm_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ProjectDTO extends AbstractUserOwnedModelDTO implements IWBS {

    private final static long serialVersionUID = 1;

    @Column
    @NotNull
    private String name = "";

    @Column
    @NotNull
    private String description = "";

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

    @Column
    @NotNull
    private Date created = new Date();

    public ProjectDTO(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
