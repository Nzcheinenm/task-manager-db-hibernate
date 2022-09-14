package ru.t1.dkononov.tm.component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class FileScanner {

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NotNull
    private Bootstrap bootstrap;

    @NotNull
    private final List<String> commands = new ArrayList<>();

    @NotNull
    private final File folder = new File("./");

    public FileScanner(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void init() {
        @Nullable final Iterable<AbstractCommand> commands = bootstrap.getCommandService().getCommandsByArgument();
        commands.forEach(e -> this.commands.add(e.getNAME()));
        es.scheduleWithFixedDelay(this::process, 0, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

    private void process() {
        for (File file: folder.listFiles()) {
            if (file.isDirectory()) continue;
            @Nullable final String fileName = file.getName();
            final boolean check = commands.contains(fileName);
            if (check) {
                try {
                    file.delete();
                    bootstrap.processCommand(fileName);
                } catch (@NotNull final Exception e) {
                    bootstrap.getLoggerService().error(e);
                }
            }
        }
    }

}
