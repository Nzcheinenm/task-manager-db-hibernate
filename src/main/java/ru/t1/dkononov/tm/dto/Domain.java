package ru.t1.dkononov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Domain implements Serializable {

    private final static long serialVersionUID = 1;

    @NotNull
    private String id = UUID.randomUUID().toString();

    @NotNull
    private Date created = new Date();

    @NotNull
    private List<User> users = new ArrayList<>();

    @NotNull
    private List<Project> projects = new ArrayList<>();

    @NotNull
    private List<Task> tasks = new ArrayList<>();

}
