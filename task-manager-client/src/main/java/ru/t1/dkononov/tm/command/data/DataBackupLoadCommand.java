package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.dto.request.DataBackupLoadRequest;
import ru.t1.dkononov.tm.enumerated.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataBackupLoadCommand extends AbstractDataCommand {

    @NotNull
    public static final String NAME = "backup-load-json";

    @Override
    public @Nullable String getARGUMENT() {
        return null;
    }

    @Override
    public @Nullable String getDESCRIPTION() {
        return null;
    }

    @Override
    public @NotNull String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        @NotNull final DataBackupLoadRequest request = new DataBackupLoadRequest();
        getDomainEndpoint().loadDataBackup(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return null;
    }


}