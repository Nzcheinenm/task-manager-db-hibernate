package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IDomainEndpoint {
    @NotNull
    @WebMethod
    DataBackupLoadResponse loadDataBackup(
            @NotNull DataBackupLoadRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataBackupSaveResponse saveDataBackup(
            @NotNull DataBackupSaveRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataBase64LoadResponse loadDataBase64(
            @NotNull DataBase64LoadRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataBase64SaveResponse saveDataBase64(
            @NotNull DataBase64SaveRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataBinarySaveResponse saveDataBinary(
            @NotNull DataBinarySaveRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataBinaryLoadResponse loadDataBinary(
            @NotNull DataBinaryLoadRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @NotNull DataJsonSaveFasterXmlRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @NotNull DataJsonLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @NotNull DataJsonSaveJaxBRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @NotNull DataJsonLoadJaxBRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @NotNull DataXmlSaveFasterXmlRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @NotNull DataXmlLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @NotNull DataXmlSaveJaxBRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @NotNull DataXmlLoadJaxBRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataYamlLoadFasterXmlResponse loadDataYaml(
            @NotNull DataYamlLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    DataYamlSaveFasterXmlResponse saveDataYaml(
            @NotNull DataYamlSaveFasterXmlRequest request
    ) throws Exception;

}
