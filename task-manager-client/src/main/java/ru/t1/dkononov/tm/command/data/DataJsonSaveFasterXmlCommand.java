package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.dto.request.DataJsonSaveFasterXmlRequest;
import ru.t1.dkononov.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class DataJsonSaveFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в json faster файл.";

    @NotNull
    public static final String NAME = "data-save-json-faster";

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
        System.out.println("[DATA SAVE JSON]");
        @NotNull final DataJsonSaveFasterXmlRequest request = new DataJsonSaveFasterXmlRequest();
        getDomainEndpoint().saveDataJsonFasterXml(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
