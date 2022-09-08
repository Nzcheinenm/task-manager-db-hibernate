package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public final class DataXmlLoadJaxBCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из xml jaxB файл.";

    @NotNull
    public static final String NAME = "data-load-xml-jaxb";

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
    public void execute() throws JAXBException {
        System.out.println("[DATA LOAD XML]");
        @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final File file = new File(FILE_XML);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
