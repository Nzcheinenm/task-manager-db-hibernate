package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataJsonLoadJaxBRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataJsonLoadJaxBCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из json jaxB файл.";

    @NotNull
    public static final String NAME = "data-load-json-jaxb";

    @Override
    public @Nullable String getARGUMENT() {
        return null;
    }

    @Override
    public @NotNull String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA LOAD JSON]");
        @NotNull final DataJsonLoadJaxBRequest request = new DataJsonLoadJaxBRequest(getToken());
        getDomainEndpoint().loadDataJsonJaxB(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
