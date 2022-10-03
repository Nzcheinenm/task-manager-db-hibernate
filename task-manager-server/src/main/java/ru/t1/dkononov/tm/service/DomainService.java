package ru.t1.dkononov.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IDomainService;
import ru.t1.dkononov.tm.component.Bootstrap;
import ru.t1.dkononov.tm.dto.Domain;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DomainService implements IDomainService {

    @NotNull
    public static final String FILE_BASE64 = "./data.base64";

    @NotNull
    public static final String FILE_BINARY = "./data.bin";

    @NotNull
    public static final String FILE_XML = "./data.xml";

    @NotNull
    public static final String FILE_JSON = "./data.json";

    @NotNull
    public static final String FILE_YAML = "./data.yaml";

    @NotNull
    public static final String FILE_BACKUP = "./data.bkp";

    @NotNull
    public final String CONTEXT_FACTORY = "javax.xml.bind.context.factory";

    @NotNull
    public final String CONTEXT_FACTORY_JAXB = "org.eclipse.persistence.jaxb.JAXBContextFactory";

    @NotNull
    public final String MEDIA_TYPE = "eclipselink.media-type";

    @NotNull
    public final String APPLICATION_TYPE_JSON = "application/json";


    @NotNull
    private final Bootstrap bootstrap;

    public DomainService(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @NotNull
    public Domain getDomain() {
        @NotNull final Domain domain = new Domain();
        domain.setProjects(bootstrap.getProjectService().findAll());
        domain.setTasks(bootstrap.getTaskService().findAll());
        domain.setUsers(bootstrap.getUserService().findAll());
        return domain;
    }

    public void setDomain(@Nullable final Domain domain) {
        if (domain == null) return;
        bootstrap.getProjectService().set(domain.getProjects());
        bootstrap.getTaskService().set(domain.getTasks());
        bootstrap.getUserService().set(domain.getUsers());
        bootstrap.getAuthService().logout();
    }

    @Override
    public void loadDataBackup() throws Exception {
        @NotNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_BACKUP));
        @NotNull final String json = new String(bytes);
        @NotNull ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

    @Override
    public void saveDataBackup() throws IOException {
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BACKUP);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(json.getBytes());
        fileOutputStream.flush();
    }



    @Override
    public void loadDataBase64() throws Exception {
        System.out.println("[DATA BASE64 LOAD]");
        @NotNull final byte[] base64Byte = Files.readAllBytes(Paths.get(FILE_BASE64));
        @Nullable final String base64Date = new String(base64Byte);
        @Nullable final byte[] bytes = new BASE64Decoder().decodeBuffer(base64Date);
        @Cleanup @NotNull final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        @Cleanup @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
    }

    @Override
    public void saveDataBase64() throws Exception {
        System.out.println("[DATA BASE64 SAVE]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BASE64);
        @NotNull final Path path = file.toPath();
        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NotNull final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @Cleanup @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(domain);

        @NotNull final byte[] bytes = byteArrayOutputStream.toByteArray();
        @NotNull final String base64 = new BASE64Encoder().encode(bytes);

        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(base64.getBytes());
    }

    @Override
    public void saveDataBinary() throws Exception {
        System.out.println("[DATA SAVE BINARY]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_BINARY);
        @NotNull final Path path = file.toPath();

        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @Cleanup @NotNull final ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
        objectInputStream.writeObject(domain);
    }

    @Override
    public void loadDataBinary() throws Exception {
        System.out.println("[DATA BINARY LOAD]");
        @Cleanup @NotNull final FileInputStream fileInputStream = new FileInputStream(FILE_BINARY);
        @Cleanup @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
    }

    @Override
    public void saveDataJsonFasterXml() throws Exception {
        System.out.println("[DATA SAVE JSON]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_JSON);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(json.getBytes());
        fileOutputStream.flush();
    }

    @Override
    public void loadDataJsonFasterXml() throws Exception {
        System.out.println("[DATA LOAD JSON]");
        @NotNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_JSON));
        @NotNull final String json = new String(bytes);
        @NotNull ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

    @Override
    public void saveDataJsonJaxB() throws Exception {
        System.out.println("[DATA SAVE JSON]");
        System.setProperty(CONTEXT_FACTORY, CONTEXT_FACTORY_JAXB);
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_JSON);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(MEDIA_TYPE, APPLICATION_TYPE_JSON);
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
    }

    @Override
    public void loadDataJsonJaxB() throws Exception {
        System.out.println("[DATA LOAD JSON]");
        System.setProperty(CONTEXT_FACTORY, CONTEXT_FACTORY_JAXB);
        @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(MEDIA_TYPE, APPLICATION_TYPE_JSON);
        @NotNull final File file = new File(FILE_JSON);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    public void saveDataXmlFasterXml() throws Exception {
        System.out.println("[DATA SAVE XML]");
        @NotNull final Domain domain = getDomain();
        @NotNull final File file = new File(FILE_XML);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
    }

    @Override
    public void loadDataXmlFasterXml() throws Exception {
        System.out.println("[DATA LOAD XML]");
        @NotNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_XML));
        @NotNull final String xml = new String(bytes);
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

    @Override
    public void saveDataXmlJaxB() throws Exception {
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
    public void loadDataXmlJaxB() throws Exception {
        System.out.println("[DATA LOAD XML]");
        @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final File file = new File(FILE_XML);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

}
