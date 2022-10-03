package ru.t1.dkononov.tm.component;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.command.data.AbstractDataCommand;
import ru.t1.dkononov.tm.command.data.DataBackupLoadCommand;
import ru.t1.dkononov.tm.command.data.DataBackupSaveCommand;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Backup {

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NotNull
    private final Bootstrap bootstrap;

    public Backup(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void init() throws Exception {
        load();
        es.scheduleWithFixedDelay(this::save, 0, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

    public void save() {
        try {
            bootstrap.processCommand(DataBackupSaveCommand.NAME, false);
        } catch (@NotNull final Exception e) {
            bootstrap.getLoggerService().error(e);
        }
    }

    public void load() throws Exception {
        if (!Files.exists(Paths.get(AbstractDataCommand.FILE_BACKUP))) return;
        bootstrap.processCommand(DataBackupLoadCommand.NAME, false);
    }

}
