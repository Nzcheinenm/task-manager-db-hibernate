package ru.t1.dkononov.tm.api.model;

import ru.t1.dkononov.tm.enumerated.Status;

public interface IHasStatus {

    Status getStatus();

    void setStatus(final Status status);

}
