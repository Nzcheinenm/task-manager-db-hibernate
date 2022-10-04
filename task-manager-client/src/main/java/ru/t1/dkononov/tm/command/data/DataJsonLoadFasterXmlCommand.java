package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.dto.request.DataJsonLoadFasterXmlRequest;
import ru.t1.dkononov.tm.enumerated.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataJsonLoadFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из json faster файл.";

    @NotNull
    public static final String NAME = "data-load-json-faster";

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
        @NotNull final DataJsonLoadFasterXmlRequest request = new DataJsonLoadFasterXmlRequest();
        getDomainEndpoint().loadDataJsonFasterXml(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
