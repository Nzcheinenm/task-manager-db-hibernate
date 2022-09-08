package ru.t1.dkononov.tm.command.data;

import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class DataXmlSaveJaxBCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в xml jaxB файл.";

    @NotNull
    public static final String NAME = "data-save-xml-jaxb";

    @Override
    public @Nullable String getARGUMENT() {
        return null;
    }

    @Override
    public @NotNull String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws IOException, JAXBException {
        System.out.println("[DATA SAVE XML]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_XML);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
