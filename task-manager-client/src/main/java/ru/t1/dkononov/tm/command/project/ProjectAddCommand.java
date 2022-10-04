package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.ProjectCreateRequest;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;


public final class ProjectAddCommand extends AbstractProjectCommand {

    @Getter
    @Nullable
    public final String DESCRIPTION = "Создать новый проект.";

    @Getter
    @Nullable
    public final String NAME = "project-add";


    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[CREATE NEW PROJECT]");
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.inLine();
        @NotNull final ProjectCreateRequest request = new ProjectCreateRequest();
        request.setName(name);
        request.setDescription(description);
        getProjectEndpoint().createProject(request);
    }

}
