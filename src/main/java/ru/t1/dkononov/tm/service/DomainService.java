package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IDomainService;
import ru.t1.dkononov.tm.command.data.*;
import ru.t1.dkononov.tm.component.Bootstrap;

public final class DomainService implements IDomainService {

    @NotNull
    private final Bootstrap bootstrap;

    public DomainService(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void loadDataBackup() throws Exception {
        bootstrap.backup.load();
    }

    @Override
    public void saveDataBackup() {
        bootstrap.backup.save();
    }

    @Override
    public void loadDataBase64() throws Exception {
        bootstrap.processCommand(DataBase64LoadCommand.NAME, false);
    }

    @Override
    public void saveDataBase64() throws Exception {
        bootstrap.processCommand(DataBase64SaveCommand.NAME, false);
    }

    @Override
    public void saveDataBinary() throws Exception {
        bootstrap.processCommand(DataBinarySaveCommand.NAME, false);
    }

    @Override
    public void loadDataBinary() throws Exception {
        bootstrap.processCommand(DataBinaryLoadCommand.NAME, false);
    }

    @Override
    public void saveDataJsonFasterXml() throws Exception {
        bootstrap.processCommand(DataJsonSaveFasterXmlCommand.NAME, false);
    }

    @Override
    public void loadDataJsonFasterXml() throws Exception {
        bootstrap.processCommand(DataJsonLoadFasterXmlCommand.NAME, false);
    }

    @Override
    public void saveDataJsonJaxB() throws Exception {
        bootstrap.processCommand(DataJsonSaveJaxBCommand.NAME, false);
    }

    @Override
    public void loadDataJsonJaxB() throws Exception {
        bootstrap.processCommand(DataJsonLoadJaxBCommand.NAME, false);
    }

    @Override
    public void saveDataXmlFasterXml() throws Exception {
        bootstrap.processCommand(DataXmlSaveFasterXmlCommand.NAME, false);
    }

    @Override
    public void loadDataXmlFasterXml() throws Exception {
        bootstrap.processCommand(DataXmlLoadFasterXmlCommand.NAME, false);
    }

    @Override
    public void saveDataXmlJaxB() throws Exception {
        bootstrap.processCommand(DataXmlSaveJaxBCommand.NAME, false);
    }

    @Override
    public void loadDataXmlJaxB() throws Exception {
        bootstrap.processCommand(DataXmlLoadJaxBCommand.NAME, false);
    }

}
