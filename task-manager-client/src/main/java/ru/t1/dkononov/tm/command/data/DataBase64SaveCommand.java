package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataBase64SaveRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataBase64SaveCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в base64 файл.";

    @NotNull
    public static final String NAME = "data-save-base64";

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
        System.out.println("[DATA BASE64 SAVE]");
        @NotNull final DataBase64SaveRequest request = new DataBase64SaveRequest(getToken());
        getDomainEndpoint().saveDataBase64(request);
    }

    @Override
    @Nullable
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
