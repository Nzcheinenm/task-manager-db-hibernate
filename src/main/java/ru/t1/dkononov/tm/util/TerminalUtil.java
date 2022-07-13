package ru.t1.dkononov.tm.util;

import ru.t1.dkononov.tm.exception.field.NumberIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String inLine() {
        return SCANNER.nextLine();
    }

    static Integer nextNumber() throws NumberIncorrectException {
        final String value = inLine();
        try {
            return Integer.parseInt(value);
        } catch (final Exception e) {
            throw new NumberIncorrectException(value, e);
        }
    }

}
