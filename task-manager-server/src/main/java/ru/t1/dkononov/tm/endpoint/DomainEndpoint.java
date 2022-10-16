package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.IDomainEndpoint;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IDomainEndpoint")
public final class DomainEndpoint extends AbstractEndpoint implements IDomainEndpoint {

    public DomainEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBackupLoadResponse loadDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBackupLoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBackup();
        return new DataBackupLoadResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBackupSaveResponse saveDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBackupSaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBackup();
        return new DataBackupSaveResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBase64LoadResponse loadDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBase64LoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBase64();
        return new DataBase64LoadResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBase64SaveResponse saveDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBase64SaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBase64();
        return new DataBase64SaveResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBinarySaveResponse saveDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBinarySaveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBinary();
        return new DataBinarySaveResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataBinaryLoadResponse loadDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataBinaryLoadRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBinary();
        return new DataBinaryLoadResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataJsonSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonFasterXml();
        return new DataJsonSaveFasterXmlResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataJsonLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonFasterXml();
        return new DataJsonLoadFasterXmlResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataJsonSaveJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonJaxB();
        return new DataJsonSaveJaxBResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataJsonLoadJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonJaxB();
        return new DataJsonLoadJaxBResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataXmlSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlFasterXml();
        return new DataXmlSaveFasterXmlResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataXmlLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlFasterXml();
        return new DataXmlLoadFasterXmlResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataXmlSaveJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlJaxB();
        return new DataXmlSaveJaxBResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataXmlLoadJaxBRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlJaxB();
        return new DataXmlLoadJaxBResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataYamlLoadFasterXmlResponse loadDataYaml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataYamlLoadFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataYaml();
        return new DataYamlLoadFasterXmlResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public DataYamlSaveFasterXmlResponse saveDataYaml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final DataYamlSaveFasterXmlRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataYaml();
        return new DataYamlSaveFasterXmlResponse();
    }


}
