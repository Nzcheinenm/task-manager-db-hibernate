package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
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
    public void execute() throws IOException {
        System.out.println("[DATA SAVE JSON]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_JSON);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(json.getBytes());
        fileOutputStream.flush();
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
