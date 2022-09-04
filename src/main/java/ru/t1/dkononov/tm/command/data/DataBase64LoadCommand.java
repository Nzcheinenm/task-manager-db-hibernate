package ru.t1.dkononov.tm.command.data;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBase64LoadCommand extends AbstractDataCommand {

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
    public void execute() throws AbstractException, IOException {
        System.out.println("[DATA BASE64 LOAD]");
        @NotNull final byte[] base64Byte = Files.readAllBytes(Paths.get(FILE_BASE64));
        @Nullable final String base64Date = new String(base64Byte);
        @Nullable final byte[] bytes = new BASE64Decoder().decodeBuffer(base64Date);
        @Cleanup @NotNull final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        @Cleanup @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
