package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IPropertyService;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Информация о разработчике";

    @Getter
    @NotNull
    public final String NAME = "about";

    @Getter
    @NotNull
    public final String ARGUMENT = "-a";


    @Override
    public void execute() {
        @NotNull final IPropertyService propertyService = getPropertyService();
        System.out.println("[ABOUT]");
        System.out.println(propertyService.getAuthorName());
        System.out.println(propertyService.getAuthorEmail());
        System.out.println();
        System.out.println("[GIT]");
        System.out.println("COMMIT ID: " + propertyService.getGitCommitId());
        System.out.println("BRANCH: " + propertyService.getGitBranch());
        System.out.println("MESSAGE: " + propertyService.getGitCommitMessage());
        System.out.println("TIME: " + propertyService.getGitCommitTime());
        System.out.println("COMMITER: " + propertyService.getGitCommiterName());
        System.out.println("E-MAIL: " + propertyService.getGitCommiterEmail());
    }

}
