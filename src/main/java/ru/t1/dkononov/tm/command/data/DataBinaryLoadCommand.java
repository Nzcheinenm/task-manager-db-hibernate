package ru.t1.dkononov.tm.command.data;

import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public final class DataBinaryLoadCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из бинарного файла";

    @NotNull
    public static final String NAME = "data-load-binary";

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
        System.out.println("[DATA BINARY LOAD]");
        @Cleanup @NotNull final FileInputStream fileInputStream = new FileInputStream(FILE_BINARY);
        @Cleanup @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
