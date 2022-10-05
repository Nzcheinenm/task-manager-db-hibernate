package ru.t1.dkononov.tm.client;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IDomainEndpointClient;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;

@NoArgsConstructor
public final class DomainEndpointClient extends AbstractEndpointClient implements IDomainEndpointClient {

    public DomainEndpointClient(@NotNull AbstractEndpointClient client) {
        super(client);
    }

    @Override
    @NotNull
    public DataBackupLoadResponse loadDataBackup(
            @NotNull final DataBackupLoadRequest request
    ) throws Exception {
        return call(request, DataBackupLoadResponse.class);
    }

    @Override
    @NotNull
    public DataBackupSaveResponse saveDataBackup(
            @NotNull final DataBackupSaveRequest request
    ) throws Exception {
        return call(request, DataBackupSaveResponse.class);
    }

    @Override
    @NotNull
    public DataBase64LoadResponse loadDataBase64(
            @NotNull final DataBase64LoadRequest request
    ) throws Exception {
        return call(request, DataBase64LoadResponse.class);
    }

    @Override
    @NotNull
    public DataBase64SaveResponse saveDataBase64(
            @NotNull final DataBase64SaveRequest request
    ) throws Exception {
        return call(request, DataBase64SaveResponse.class);
    }

    @Override
    @NotNull
    public DataBinarySaveResponse saveDataBinary(
            @NotNull final DataBinarySaveRequest request
    ) throws Exception {
        return call(request, DataBinarySaveResponse.class);
    }

    @Override
    @NotNull
    public DataBinaryLoadResponse loadDataBinary(
            @NotNull final DataBinaryLoadRequest request
    ) throws Exception {
        return call(request, DataBinaryLoadResponse.class);
    }

    @Override
    @NotNull
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @NotNull final DataJsonSaveFasterXmlRequest request
    ) throws Exception {
        return call(request, DataJsonSaveFasterXmlResponse.class);
    }

    @Override
    @NotNull
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @NotNull final DataJsonLoadFasterXmlRequest request
    ) throws Exception {
        return call(request, DataJsonLoadFasterXmlResponse.class);
    }

    @Override
    @NotNull
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @NotNull final DataJsonSaveJaxBRequest request
    ) throws Exception {
        return call(request, DataJsonSaveJaxBResponse.class);
    }

    @Override
    @NotNull
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @NotNull final DataJsonLoadJaxBRequest request
    ) throws Exception {
        return call(request, DataJsonLoadJaxBResponse.class);
    }

    @Override
    @NotNull
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @NotNull final DataXmlSaveFasterXmlRequest request
    ) throws Exception {
        return call(request, DataXmlSaveFasterXmlResponse.class);
    }

    @Override
    @NotNull
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @NotNull final DataXmlLoadFasterXmlRequest request
    ) throws Exception {
        return call(request, DataXmlLoadFasterXmlResponse.class);
    }

    @Override
    @NotNull
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @NotNull final DataXmlSaveJaxBRequest request
    ) throws Exception {
        return call(request, DataXmlSaveJaxBResponse.class);
    }

    @Override
    @NotNull
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @NotNull final DataXmlLoadJaxBRequest request
    ) throws Exception {
        return call(request, DataXmlLoadJaxBResponse.class);
    }

    @Override
    @NotNull
    public DataYamlLoadFasterXmlResponse loadDataYaml(@NotNull final DataYamlLoadFasterXmlRequest request) throws Exception {
        return call(request, DataYamlLoadFasterXmlResponse.class);
    }

    @Override
    @NotNull
    public DataYamlSaveFasterXmlResponse saveDataYaml(@NotNull final DataYamlSaveFasterXmlRequest request) throws Exception {
        return call(request, DataYamlSaveFasterXmlResponse.class);
    }

}
