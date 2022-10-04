package ru.t1.dkononov.tm.component;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ru.t1.dkononov.tm.service.DomainService.FILE_BACKUP;

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
            bootstrap.getDomainService().saveDataBackup();
        } catch (@NotNull final Exception e) {
            bootstrap.getLoggerService().error(e);
        }
    }

    public void load() throws Exception {
        if (!Files.exists(Paths.get(FILE_BACKUP))) return;
        bootstrap.getDomainService().loadDataBackup();
    }

}
