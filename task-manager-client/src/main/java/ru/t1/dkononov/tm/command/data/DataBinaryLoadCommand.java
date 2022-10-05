package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataBinaryLoadRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataBinaryLoadCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из бинарного файла";

    @NotNull
    public static final String NAME = "data-load-binary";

    @Override
    @Nullable
    public String getARGUMENT() {
        return null;
    }

    @Override
    @NotNull
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    @NotNull
    public String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA BINARY LOAD]");
        @NotNull final DataBinaryLoadRequest request = new DataBinaryLoadRequest();
        getDomainEndpoint().loadDataBinary(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
