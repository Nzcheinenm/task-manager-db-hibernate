package ru.t1.dkononov.tm.model;

import java.util.UUID;

public abstract class AbstractModel {

    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
