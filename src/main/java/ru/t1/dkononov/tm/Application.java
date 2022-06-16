package ru.t1.dkononov.tm;

import ru.t1.dkononov.tm.api.ICommandRepository;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.context.Bootstrap;
import ru.t1.dkononov.tm.controller.CommandController;
import ru.t1.dkononov.tm.model.Command;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.service.CommandService;
import ru.t1.dkononov.tm.util.FormatUtil;

import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}

