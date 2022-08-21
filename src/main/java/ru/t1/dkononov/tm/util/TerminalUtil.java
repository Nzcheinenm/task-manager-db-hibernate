package ru.t1.dkononov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.field.NumberIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    @NotNull
    Scanner SCANNER = new Scanner(System.in);

    @NotNull
    static String inLine() {
        return SCANNER.nextLine();
    }

    @NotNull
    static Integer nextNumber() throws NumberIncorrectException {
        final String value = inLine();
        try {
            return Integer.parseInt(value);
        } catch (final Exception e) {
            throw new NumberIncorrectException(value, e);
        }
    }

}
