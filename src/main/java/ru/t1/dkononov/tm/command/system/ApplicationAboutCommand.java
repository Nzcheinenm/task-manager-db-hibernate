package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
        System.out.println("[ABOUT]");
        System.out.println(getPropertyService().getAuthorName());
        System.out.println(getPropertyService().getAuthorEmail());
    }

}
