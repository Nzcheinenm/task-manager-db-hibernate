package ru.t1.dkononov.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tm_task")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public final class Task extends AbstractUserOwnedModel implements IWBS {

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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public Task(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
