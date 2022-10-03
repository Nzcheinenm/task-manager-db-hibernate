package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.ILoggerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public final class LoggerService implements ILoggerService {

    @NotNull
    private static final String CONFIG_FILE = "/logger.properties";

    @NotNull
    private static final String COMMANDS = "COMMANDS";

    @NotNull
    private static final String COMMANDS_FILE = "./commands.xml";

    @NotNull
    private static final String ERRORS = "ERRORS";

    @NotNull
    private static final String ERRORS_FILE = "./errors.xml";

    @NotNull
    private static final String MESSAGES = "MESSAGES";

    @NotNull
    private static final String MESSAGES_FILE = "./messages.xml";

    @NotNull
    private static final LogManager MANAGER = LogManager.getLogManager();

    @NotNull
    private static final Logger LOGGER_ROOT = Logger.getLogger("");

    @NotNull
    private static final Logger LOGGER_COMMAND = Logger.getLogger("COMMANDS");

    @NotNull
    private static final Logger LOGGER_ERROR = Logger.getLogger("ERRORS");

    @NotNull
    private static final Logger LOGGER_MESSAGE = Logger.getLogger("MESSAGES");

    @NotNull
    private static final ConsoleHandler CONSOLE_HANDLER = getConsoleHandler();

    @NotNull
    public static Logger getLoggerCommand() {
        return Logger.getLogger(COMMANDS);
    }

    @NotNull
    public static Logger getLoggerError() {
        return LOGGER_ERROR;
    }

    @NotNull
    public static Logger getLoggerMessage() {
        return LOGGER_MESSAGE;
    }

    static {
        loadConfigFromFile();
        registry(LOGGER_COMMAND, COMMANDS_FILE, false);
        registry(LOGGER_ERROR, ERRORS_FILE, true);
        registry(LOGGER_MESSAGE, MESSAGES_FILE, true);
    }

    private static void loadConfigFromFile() {
        try {
            @NotNull final Class<?> clazz = LoggerService.class;
            @Nullable final InputStream inputStream = clazz.getResourceAsStream(CONFIG_FILE);
            MANAGER.readConfiguration(inputStream);
        } catch (final IOException e) {
            LOGGER_ROOT.severe(e.getMessage());
        }
    }

    private static void registry(
            @NotNull final Logger logger,
            @Nullable final String commandsFile,
            final boolean isConsole
    ) {
        try {
            if (isConsole) logger.addHandler(CONSOLE_HANDLER);
            logger.setUseParentHandlers(false);
            if (commandsFile != null && !commandsFile.isEmpty())
                logger.addHandler(new FileHandler(commandsFile));
        } catch (final IOException e) {
            LOGGER_ROOT.severe(e.getMessage());
        }
    }

    @NotNull
    private static ConsoleHandler getConsoleHandler() {
        @NotNull final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        return handler;
    }

    @Override
    public void info(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_MESSAGE.info(message);
    }

    @Override
    public void debug(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_MESSAGE.fine(message);
    }

    @Override
    public void command(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_COMMAND.info(message);
    }

    @Override
    public void error(@Nullable final Exception e) {
        if (e == null) return;
        LOGGER_ERROR.log(Level.SEVERE, e.getMessage(), e);
    }

}
