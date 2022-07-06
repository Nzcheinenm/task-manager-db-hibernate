package ru.t1.dkononov.tm.model;

import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.Date;
import java.util.UUID;

public final class Task implements IWBS {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

    private Status status = Status.NOT_STARTED;

    private String projectId;

    private Date created = new Date();

    public Task() {
    }

    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(final String projectId) {
        this.projectId = projectId;
    }

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
