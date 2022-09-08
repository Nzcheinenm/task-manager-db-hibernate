package ru.t1.dkononov.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.Domain;
import ru.t1.dkononov.tm.enumerated.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataXmlLoadFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из xml faster файл.";

    @NotNull
    public static final String NAME = "data-load-xml-faster";

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
    public void execute() throws IOException {
        System.out.println("[DATA LOAD XML]");
        @NotNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_XML));
        @NotNull final String xml = new String(bytes);
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
