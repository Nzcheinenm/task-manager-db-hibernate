package ru.t1.dkononov.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tm_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Project extends AbstractUserOwnedModel implements IWBS {

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

    @NotNull
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @Column
    @NotNull
    private Date created = new Date();

    public Project(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
