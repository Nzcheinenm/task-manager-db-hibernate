package ru.t1.dkononov.tm.exception.system;

public final class ArgumentNotSupportedException extends AbstractSystemException {

    public ArgumentNotSupportedException(final String data) {
        super("Error! This Argument \"" + data + "\" is not supported...");
    }

}
