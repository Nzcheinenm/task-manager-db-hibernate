package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataBinarySaveRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataBinarySaveCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в бинарный файл";

    @NotNull
    public static final String NAME = "data-save-binary";

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
        System.out.println("[DATA SAVE BINARY]");
        @NotNull final DataBinarySaveRequest request = new DataBinarySaveRequest();
        getDomainEndpoint().saveDataBinary(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
