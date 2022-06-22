package ru.t1.dkononov.tm.util;

import java.util.Scanner;

public interface TerminalUtil {
    final Scanner scanner = new Scanner(System.in);

    static String inLine() {
        return scanner.nextLine();
    }
}
