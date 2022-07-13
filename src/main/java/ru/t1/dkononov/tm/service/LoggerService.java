package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.services.ILoggerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public final class LoggerService implements ILoggerService {

    private static final String CONFIG_FILE = "/logger.properties";

    private static final String COMMANDS = "COMMANDS";

    private static final String COMMANDS_FILE = "./commands.xml";

    private static final String ERRORS = "ERRORS";

    private static final String ERRORS_FILE = "./errors.xml";

    private static final String MESSAGES = "MESSAGES";

    private static final String MESSAGES_FILE = "./messages.xml";

    private static final LogManager MANAGER = LogManager.getLogManager();

    private static final Logger LOGGER_ROOT = Logger.getLogger("");

    private static final Logger LOGGER_COMMAND = Logger.getLogger("COMMANDS");

    private static final Logger LOGGER_ERROR = Logger.getLogger("ERRORS");

    private static final Logger LOGGER_MESSAGE = Logger.getLogger("MESSAGES");

    private static final ConsoleHandler CONSOLE_HANDLER = getConsoleHandler();

    public static Logger getLoggerCommand() {
        return Logger.getLogger(COMMANDS);
    }

    public static Logger getLoggerError() {
        return LOGGER_ERROR;
    }

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
            final Class<?> clazz = LoggerService.class;
            final InputStream inputStream = clazz.getResourceAsStream(CONFIG_FILE);
            MANAGER.readConfiguration(inputStream);
        } catch (final IOException e) {
            LOGGER_ROOT.severe(e.getMessage());
        }
    }

    private static void registry(
            final Logger logger,
            final String commandsFile,
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

    private static ConsoleHandler getConsoleHandler() {
        final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        return handler;
    }

    @Override
    public void info(final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_MESSAGE.info(message);
    }

    @Override
    public void debug(final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_MESSAGE.fine(message);
    }

    @Override
    public void command(final String message) {
        if (message == null || message.isEmpty()) return;
        LOGGER_COMMAND.info(message);
    }

    @Override
    public void error(final Exception e) {
        if (e == null) return;
        LOGGER_ERROR.log(Level.SEVERE, e.getMessage(), e);
    }

}
