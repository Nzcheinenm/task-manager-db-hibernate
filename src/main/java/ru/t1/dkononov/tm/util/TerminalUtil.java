package ru.t1.dkononov.tm.util;

import java.util.Scanner;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String inLine() {
        return SCANNER.nextLine();
    }

}
