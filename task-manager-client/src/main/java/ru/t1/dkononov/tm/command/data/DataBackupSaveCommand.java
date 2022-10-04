package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.dto.request.DataBackupSaveRequest;
import ru.t1.dkononov.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class DataBackupSaveCommand extends AbstractDataCommand {

    @NotNull
    public static final String NAME = "backup-save-json";

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
        @NotNull final DataBackupSaveRequest request = new DataBackupSaveRequest();
        getDomainEndpoint().saveDataBackup(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return null;
    }

}
