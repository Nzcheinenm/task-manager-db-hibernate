package ru.t1.dkononov.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.t1.dkononov.tm.api.endpoint.*;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.api.services.ILoggerService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.command.server.ConnectCommand;
import ru.t1.dkononov.tm.command.server.DisconnectCommand;
import ru.t1.dkononov.tm.exception.system.ArgumentNotSupportedException;
import ru.t1.dkononov.tm.exception.system.CommandNotSupportedException;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.service.CommandService;
import ru.t1.dkononov.tm.service.LoggerService;
import ru.t1.dkononov.tm.service.PropertyService;
import ru.t1.dkononov.tm.util.SystemUtil;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private static final String PACKAGE_COMMAND = "ru.t1.dkononov.tm.command";

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @Getter
    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @Getter
    @NotNull
    private final ILoggerService loggerService = new LoggerService();


    @Getter
    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @Getter
    @NotNull
    private final ISystemEndpoint systemEndpointClient = ISystemEndpoint.newInstance(propertyService);

    @Getter
    @NotNull
    private final IDomainEndpoint domainEndpointClient = IDomainEndpoint.newInstance(propertyService);

    @Getter
    @NotNull
    private final IProjectEndpoint projectEndpointClient = IProjectEndpoint.newInstance(propertyService);

    @Getter
    @NotNull
    private final ITaskEndpoint taskEndpointClient = ITaskEndpoint.newInstance(propertyService);

    @Getter
    @NotNull
    private final IUserEndpoint userEndpointClient = IUserEndpoint.newInstance(propertyService);

    @Getter
    @NotNull
    private final IAuthEndpoint authEndpointClient = IAuthEndpoint.newInstance(propertyService);


    @NotNull
    private final FileScanner fileScanner = new FileScanner(this);

    {
        @NotNull final Reflections reflections = new Reflections(PACKAGE_COMMAND);
        @NotNull final Set<Class<? extends AbstractCommand>> classes =
                reflections.getSubTypesOf(AbstractCommand.class);
        for (@NotNull final Class<? extends AbstractCommand> clazz : classes) {
            try {
                registry(clazz);
            } catch (@NotNull final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void run(@NotNull final String[] args) {
        if (processArgument(args)) System.exit(0);
        init();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                @NotNull final String command = TerminalUtil.inLine();
                processCommand(command);
                System.out.println("[OK]");
                loggerService.command(command);
            } catch (@NotNull final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    private void init() {
        try {
            prepareStart();
        } catch (final Exception e) {
            loggerService.error(e);
            System.err.println("[INIT FAIL]");
        }
    }

    private void prepareStart() throws Exception {
        initPID();
        initLogger();
        Runtime.getRuntime().addShutdownHook(new Thread(this::prepareShutdown));
        fileScanner.init();
    }

    private void prepareShutdown() {
        fileScanner.stop();
        loggerService.info("** TASK-MANAGER IS SHUTTING DOWN **");
    }

    private void processArgument(@Nullable final String argument)
            throws Exception {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByArgument(argument);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(argument);
        abstractCommand.execute();
    }

    private boolean processArgument(@Nullable final String[] args) {
        if (args == null || args.length == 0) return false;
        @Nullable final String argument = args[0];
        try {
            processArgument(argument);
            return true;
        } catch (@NotNull final Exception e) {
            loggerService.error(e);
            return false;
        }
    }

    private void initPID() throws IOException {
        @NotNull final String filename = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(filename), pid.getBytes());
        @NotNull final File file = new File(filename);
        file.deleteOnExit();
    }


    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER **");
    }

    public void processCommand(@Nullable final String command, final boolean checkRoles)
            throws Exception {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        abstractCommand.execute();
    }

    void processCommand(@Nullable final String command)
            throws Exception {
        processCommand(command, true);
    }

    private void registry(@NotNull final Class<? extends AbstractCommand> clazz) throws Exception {
        if (Modifier.isAbstract(clazz.getModifiers())) return;
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        @NotNull final AbstractCommand command = clazz.newInstance();
        registry(command);
    }

    private void registry(@NotNull final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

}
