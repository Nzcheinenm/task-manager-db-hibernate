package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.IDomainEndpoint;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DomainEndpoint extends AbstractEndpoint implements IDomainEndpoint {

    public DomainEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @Override
    @NotNull
    public DataBackupLoadResponse loadDataBackup(
            @NotNull final DataBackupLoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBackup();
        return new DataBackupLoadResponse();
    }

    @Override
    @NotNull
    public DataBackupSaveResponse saveDataBackup(
            @NotNull final DataBackupSaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBackup();
        return new DataBackupSaveResponse();
    }

    @Override
    @NotNull
    public DataBase64LoadResponse loadDataBase64(
            @NotNull final DataBase64LoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBase64();
        return new DataBase64LoadResponse();
    }

    @Override
    @NotNull
    public DataBase64SaveResponse saveDataBase64(
            @NotNull final DataBase64SaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBase64();
        return new DataBase64SaveResponse();
    }

    @Override
    @NotNull
    public DataBinarySaveResponse saveDataBinary(
            @NotNull final DataBinarySaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBinary();
        return new DataBinarySaveResponse();
    }

    @Override
    @NotNull
    public DataBinaryLoadResponse loadDataBinary(
            @NotNull final DataBinaryLoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBinary();
        return new DataBinaryLoadResponse();
    }

    @Override
    @NotNull
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @NotNull final DataJsonSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonFasterXml();
        return new DataJsonSaveFasterXmlResponse();
    }

    @Override
    @NotNull
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @NotNull final DataJsonLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonFasterXml();
        return new DataJsonLoadFasterXmlResponse();
    }

    @Override
    @NotNull
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @NotNull final DataJsonSaveJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonJaxB();
        return new DataJsonSaveJaxBResponse();
    }

    @Override
    @NotNull
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @NotNull final DataJsonLoadJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonJaxB();
        return new DataJsonLoadJaxBResponse();
    }

    @Override
    @NotNull
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @NotNull final DataXmlSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlFasterXml();
        return new DataXmlSaveFasterXmlResponse();
    }

    @Override
    @NotNull
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @NotNull final DataXmlLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlFasterXml();
        return new DataXmlLoadFasterXmlResponse();
    }

    @Override
    @NotNull
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @NotNull final DataXmlSaveJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlJaxB();
        return new DataXmlSaveJaxBResponse();
    }

    @Override
    @NotNull
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @NotNull final DataXmlLoadJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlJaxB();
        return new DataXmlLoadJaxBResponse();
    }

    @Override
    @NotNull
    public DataYamlLoadFasterXmlResponse loadDataYaml(
            @NotNull final DataYamlLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataYaml();
        return new DataYamlLoadFasterXmlResponse();
    }

    @Override
    @NotNull
    public DataYamlSaveFasterXmlResponse saveDataYaml(
            @NotNull final DataYamlSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataYaml();
        return new DataYamlSaveFasterXmlResponse();
    }


}
