package ru.t1.dkononov.tm;

import ru.t1.dkononov.tm.component.Bootstrap;

public final class Application {

    public static void main(final String[] args) {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}

