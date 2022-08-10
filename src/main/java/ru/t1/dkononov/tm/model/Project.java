package ru.t1.dkononov.tm.model;

import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.Date;

public final class Project extends AbstractUserOwnedModel implements IWBS {

    private String name = "";

    private Status status = Status.NOT_STARTED;

    private String description = "";

    private Date created = new Date();

    public Project() {

    }

    public Project(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
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

    public void setStatus(final Status status) {
        this.status = status;
    }
}
