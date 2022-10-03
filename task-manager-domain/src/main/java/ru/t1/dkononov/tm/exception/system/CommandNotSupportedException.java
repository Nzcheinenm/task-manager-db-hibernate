package ru.t1.dkononov.tm.exception.system;

public final class CommandNotSupportedException extends AbstractSystemException {

    public CommandNotSupportedException(final String data) {
        super("Error! This Command \"" + data + "\" is not supported...");
    }

}
