package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

public final class ApplicationVersionCommand extends AbstractSystemCommand {


    @Getter
    @NotNull
    public final String DESCRIPTION = "Версия приложения";

    @Getter
    @NotNull
    public final String NAME = "version";

    @Getter
    @NotNull
    public final String ARGUMENT = "-version";

    @Override
    public void execute() throws Exception {
        System.out.println(getPropertyService().getApplicationVersion());
        @NotNull final ApplicationVersionRequest request = new ApplicationVersionRequest();
        @NotNull final ApplicationVersionResponse serverAboutResponse = getSystemEndpoint().getVersion(request);
        System.out.println(serverAboutResponse.getVersion());
    }

}
