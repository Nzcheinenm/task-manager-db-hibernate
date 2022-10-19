package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataBackupSaveRequest;
import ru.t1.dkononov.tm.enumerated.Role;

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
        @NotNull final DataBackupSaveRequest request = new DataBackupSaveRequest(getToken());
        getDomainEndpoint().saveDataBackup(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return null;
    }

}
