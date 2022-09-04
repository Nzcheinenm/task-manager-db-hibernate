package ru.t1.dkononov.tm.command.data;

import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DateBinarySaveCommand extends AbstractDataCommand {

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
    public void execute() throws AbstractException, IOException {
        System.out.println("[DATA SAVE BINARY]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BINARY);
        @NotNull final Path path = file.toPath();

        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @Cleanup @NotNull final ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
        objectInputStream.writeObject(domain);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
