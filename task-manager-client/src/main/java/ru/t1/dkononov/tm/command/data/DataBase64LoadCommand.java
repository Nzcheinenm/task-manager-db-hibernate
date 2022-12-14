package ru.t1.dkononov.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataBase64LoadRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataBase64LoadCommand extends AbstractDataCommand {

    @NotNull
    public static final String NAME = "data-load-base64";

    @NotNull
    public static final String DESCRIPTION = "Загрузка данных из файла .base64.";


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


    @SneakyThrows
    @Override
    public void execute() throws Exception {
        System.out.println("[DATA BASE64 LOAD]");
        @NotNull final DataBase64LoadRequest request = new DataBase64LoadRequest(getToken());
        getDomainEndpoint().loadDataBase64(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
