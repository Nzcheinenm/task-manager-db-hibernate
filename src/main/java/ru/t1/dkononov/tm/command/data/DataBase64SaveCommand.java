package ru.t1.dkononov.tm.command.data;

import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataBase64SaveCommand extends AbstractDataCommand {

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
    public void execute() throws AbstractException, IOException, ClassNotFoundException {
        System.out.println("[DATA BASE64 SAVE]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BASE64);
        @NotNull final Path path = file.toPath();
        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NotNull final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @Cleanup @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(domain);

        @NotNull final byte[] bytes = byteArrayOutputStream.toByteArray();
        @NotNull final String base64 = new BASE64Encoder().encode(bytes);

        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(base64.getBytes());
    }

    @Override
    @Nullable
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
