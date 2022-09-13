package ru.t1.dkononov.tm.component;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.command.data.AbstractDataCommand;
import ru.t1.dkononov.tm.command.data.DataBackupLoadCommand;
import ru.t1.dkononov.tm.command.data.DataBackupSaveCommand;
import ru.t1.dkononov.tm.exception.AbstractException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Backup extends Thread {

    @NotNull
    private final Bootstrap bootstrap;

    public Backup(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        this.setDaemon(true);
    }

    public void init() throws AbstractException, JAXBException, IOException, ClassNotFoundException {
        load();
        start();
    }

    public void save() throws AbstractException, JAXBException, IOException, ClassNotFoundException {
        bootstrap.processCommand(DataBackupSaveCommand.NAME, false);
    }

    public void load() throws AbstractException, JAXBException, IOException, ClassNotFoundException {
        if (!Files.exists(Paths.get(AbstractDataCommand.FILE_BACKUP))) return;
        bootstrap.processCommand(DataBackupLoadCommand.NAME, false);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(3000);
                save();
            } catch (@NotNull final Exception e) {
                bootstrap.getLoggerService().error(e);
            }
        }
    }

}
