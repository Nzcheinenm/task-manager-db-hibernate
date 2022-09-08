package ru.t1.dkononov.tm.api.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ICommand {

    @Nullable
    String getARGUMENT();

    @NotNull
    String getDESCRIPTION();

    @NotNull
    String getNAME();

    void execute() throws AbstractException, IOException, ClassNotFoundException, JAXBException;

}
