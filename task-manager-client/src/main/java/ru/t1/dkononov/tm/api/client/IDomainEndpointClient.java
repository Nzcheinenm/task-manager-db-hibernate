package ru.t1.dkononov.tm.api.client;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;

public interface IDomainEndpointClient {
    @NotNull DataBackupLoadResponse loadDataBackup(
            @NotNull DataBackupLoadRequest request
    ) throws Exception;

    @NotNull DataBackupSaveResponse saveDataBackup(
            @NotNull DataBackupSaveRequest request
    ) throws Exception;

    @NotNull DataBase64LoadResponse loadDataBase64(
            @NotNull DataBase64LoadRequest request
    ) throws Exception;

    @NotNull DataBase64SaveResponse saveDataBase64(
            @NotNull DataBase64SaveRequest request
    ) throws Exception;

    @NotNull DataBinarySaveResponse saveDataBinary(
            @NotNull DataBinarySaveRequest request
    ) throws Exception;

    @NotNull DataBinaryLoadResponse loadDataBinary(
            @NotNull DataBinaryLoadRequest request
    ) throws Exception;

    @NotNull DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @NotNull DataJsonSaveFasterXmlRequest request
    ) throws Exception;

    @NotNull DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @NotNull DataJsonLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @NotNull DataJsonSaveJaxBRequest request
    ) throws Exception;

    @NotNull DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @NotNull DataJsonLoadJaxBRequest request
    ) throws Exception;

    @NotNull DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @NotNull DataXmlSaveFasterXmlRequest request
    ) throws Exception;

    @NotNull DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @NotNull DataXmlLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @NotNull DataXmlSaveJaxBRequest request
    ) throws Exception;

    @NotNull DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @NotNull DataXmlLoadJaxBRequest request
    ) throws Exception;

    @NotNull DataYamlLoadFasterXmlResponse loadDataYaml(
            @NotNull DataYamlLoadFasterXmlRequest request
    ) throws Exception;

    @NotNull DataYamlSaveFasterXmlResponse saveDataYaml(
            @NotNull DataYamlSaveFasterXmlRequest request
    ) throws Exception;

}
