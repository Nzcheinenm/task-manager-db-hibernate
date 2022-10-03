package ru.t1.dkononov.tm;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.component.Bootstrap;

public final class Application {

    public static void main(@NotNull final String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}

