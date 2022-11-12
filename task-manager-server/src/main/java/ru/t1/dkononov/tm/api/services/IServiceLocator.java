package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.dto.IProjectDTOService;
import ru.t1.dkononov.tm.api.services.dto.IProjectTaskDTOService;
import ru.t1.dkononov.tm.api.services.dto.ITaskDTOService;
import ru.t1.dkononov.tm.api.services.dto.IUserDTOService;

public interface IServiceLocator {

    @NotNull
    IAuthService getAuthService();

    @NotNull
    IUserDTOService getUserService();

    @NotNull
    IProjectDTOService getProjectService();

    @NotNull
    IProjectTaskDTOService getProjectTaskService();

    @NotNull
    ITaskDTOService getTaskService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    IDomainService getDomainService();

}
