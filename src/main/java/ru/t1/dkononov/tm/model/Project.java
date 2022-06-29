package ru.t1.dkononov.tm.model;

import ru.t1.dkononov.tm.enumerated.Status;

import java.util.UUID;

public final class Project {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private Status status = Status.NOT_STARTED;

    private String description = "";

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
